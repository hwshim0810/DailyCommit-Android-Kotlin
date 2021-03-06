package xyz.laziness.dailycommit.ui.modules.main.friends.presenter

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import javax.inject.Inject


class FriendsStatusPresenterImpl<V: FriendsStatusView, I: FriendsStatusInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable):
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        FriendsStatusPresenter<V, I> {

    companion object {

        var currentId: Long = 0L

    }


    override fun doMyContributionRequest() {
        getView()?.showProgress()
        interactor?.let {
            compositeDisposable.add(
                    it.doMyContributionRequest()
                            .compose(schedulerHelper.ioToMainSingleScheduler())
                            .subscribe({
                                getView()?.run{
                                    displayMyContributions(it)
                                    hideProgress()
                                }
                            }, {
                                this.onError()
                            })
            )
        }
    }

    override fun doFriendsContributionRequest() {
        getView()?.run {
            isLoading = true
            showProgress()
        }

        val interactor = interactor
        interactor?.let {
            compositeDisposable.add(
                it.loadFriends(currentId)
                        .compose(schedulerHelper.ioToMainObservableScheduler())
                        .flatMapIterable { it }
                        .concatMap {
                            val friendName = it.friendName
                            currentId = it.id
                            interactor.doContributionRequest(friendName)
                                    .compose(schedulerHelper.ioToMainSingleScheduler())
                                    .toObservable()
                                    .doOnNext {
                                        getView()?.displayFriendContributions(it, friendName)
                                    }
                        }.subscribe({}, {
                            this.onError()
                        }, {
                            getView()?.run {
                                hideProgress()
                                isLoading = false
                            }
                        })
            )
        }
    }

    override fun doFriendContributionRequest(friendName: String) {
        getView()?.run {
            isLoading = true
            showProgress()
        }

        interactor?.let {
            compositeDisposable.add(
                    it.doContributionRequest(friendName)
                            .compose(schedulerHelper.ioToMainSingleScheduler())
                            .subscribe({
                                getView()?.run {
                                    displayFriendContributions(it, friendName)
                                    hideProgress()
                                }
                            }, {
                                this.onError()
                            })
            )
        }
    }

    override fun onFriendDeleteButtonClicked(friendName: String, pos: Int) {
        val interactor = interactor
        interactor?.let {
            compositeDisposable.add(
                    it.loadFriendByName(friendName)
                            .subscribeOn(Schedulers.io())
                            .flatMap {
                                interactor.deleteFriend(it)
                                        .compose(schedulerHelper.ioToMainObservableScheduler())
                            }
                            .subscribe({
                                getView()?.onResponseDeleteFriend(it, pos)
                            }, {
                                this.onError()
                            })
            )
        }
    }
}