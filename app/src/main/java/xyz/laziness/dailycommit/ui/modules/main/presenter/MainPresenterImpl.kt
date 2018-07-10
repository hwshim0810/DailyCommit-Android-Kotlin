package xyz.laziness.dailycommit.ui.modules.main.presenter

import com.androidnetworking.error.ANError
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.data.network.github.response.ErrorResponse
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.interactor.MainInteractor
import xyz.laziness.dailycommit.ui.modules.main.view.MainView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import java.net.HttpURLConnection
import javax.inject.Inject


class MainPresenterImpl<V: MainView, I: MainInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        MainPresenter<V, I> {

    override fun onAttach(view: V?) {
        super.onAttach(view)
        userInfoRequest()
        setUserStatusDisplay()
    }

    override fun setUserStatusDisplay() = getView()?.openUserStatusFragment()

    override fun userInfoRequest() {
        interactor?.let {
            compositeDisposable.add(
                    it.doUserInfoApiCall()
                            .compose(schedulerHelper.ioToMainObservableScheduler())
                            .subscribe({
                                getView()?.setViewData(it)
                            }, {
                                it as ANError
                                if (it.errorCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                                    getView()
                                else
                                    this.onError()
                            })

            )
        }
    }

    override fun onDrawerLogoutItemClick() {
        interactor?.let {
            compositeDisposable.add(
                    Observable.fromCallable {
                        it.removeUserInfoInPreference()
                    }
                    .compose(schedulerHelper.ioToMainObservableScheduler())
                    .subscribe(
                        {
                            getView()?.run {
                                sendLogoutBroadCast()
                                startLoginActivity()
                            }
                        },
                        { getView()?.showErrorMessage() }
                    )
            )
        }
    }

    override fun onAddFriendDialogOkClick(friendName: String) {
        interactor?.let {
            compositeDisposable.add(
                    it.doPublicUserInfoApiCall(friendName)
                            .compose(schedulerHelper.ioToMainObservableScheduler())
                            .subscribe({
                                if (it.second) getView()?.onResponseAddingFriend(friendName, it.first)
                                else getView()?.showToastMessage(R.string.user_add_fail_msg)
                            }, {
                                it as ANError
                                if (it.errorCode != 0)
                                    getView()?.showToastMessage(it.getErrorAsObject(ErrorResponse::class.java).message)
                                else
                                    this.onError()
                            })
            )
        }

    }
}
