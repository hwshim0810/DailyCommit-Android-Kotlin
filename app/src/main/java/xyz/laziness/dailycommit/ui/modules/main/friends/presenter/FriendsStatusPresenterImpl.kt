package xyz.laziness.dailycommit.ui.modules.main.friends.presenter

import io.reactivex.disposables.CompositeDisposable
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

    override fun doMyContributionRequest() {
        interactor?.let {
            compositeDisposable.add(
                    it.doMyContributionRequest()
                            .compose(schedulerHelper.ioToMainSingleScheduler())
                            .subscribe({
                                getView()?.displayMyContributions(it)
                            }, {
                                this.onError()
                            })
            )
        }
    }

    override fun doFriendsContributionRequest() {
        val interactor = interactor
        interactor?.let {
            compositeDisposable.add(
                it.loadFriends()
                        .compose(schedulerHelper.ioToMainObservableScheduler())
                        .flatMapIterable { it }
                        .concatMap {
                            val friendName = it.friendName
                            interactor.doContributionRequest(friendName)
                                    .compose(schedulerHelper.ioToMainSingleScheduler())
                                    .toObservable()
                                    .doOnNext { getView()?.displayFriendContributions(it, friendName) }
                        }.subscribe({}, { this.onError() })
            )
        }
    }

    override fun doFriendContributionRequest(friendName: String) {
        interactor?.let {
            compositeDisposable.add(
                    it.doContributionRequest(friendName)
                            .compose(schedulerHelper.ioToMainSingleScheduler())
                            .subscribe({
                                getView()?.displayFriendContributions(it, friendName)
                            }, {
                                this.onError()
                            })
            )
        }
    }

}