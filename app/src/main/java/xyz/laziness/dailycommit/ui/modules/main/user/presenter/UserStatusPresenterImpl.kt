package xyz.laziness.dailycommit.ui.modules.main.user.presenter

import com.androidnetworking.error.ANError
import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.R
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.main.user.interactor.UserStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.user.view.UserStatusView
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import java.net.HttpURLConnection
import javax.inject.Inject


class UserStatusPresenterImpl<V: UserStatusView, I: UserStatusInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable):
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        UserStatusPresenter<V, I> {

    override fun userInfoRequest() {
        val interactor = interactor
        interactor?.let {
            compositeDisposable.add(
                    it.doUserInfoApiCall()
                            .compose(schedulerHelper.ioToMainObservableScheduler())
                            .subscribe({
                                interactor.updateUserName(it)
                                getView()?.setUiData(it)
                            }, {
                                if (it is ANError) {
                                    if (it.errorCode == HttpURLConnection.HTTP_UNAUTHORIZED)
                                        this.onError(R.string.invalid_token_msg)
                                    else
                                        this.onError()
                                }
                            })

            )
        }
    }
}