package xyz.laziness.dailycommit.ui.modules.login.input.presenter

import com.androidnetworking.error.ANError
import io.reactivex.disposables.CompositeDisposable
import xyz.laziness.dailycommit.data.network.github.GitHubApiConstants
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenterImpl
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractor
import xyz.laziness.dailycommit.ui.modules.login.input.view.LoginInputView
import xyz.laziness.dailycommit.utils.AppConstants
import xyz.laziness.dailycommit.utils.io.SchedulerHelper
import java.net.HttpURLConnection
import javax.inject.Inject


class LoginInputPresenterImpl<V: LoginInputView, I: LoginInputInteractor>
@Inject internal constructor(interactor: I,
                             schedulerHelper: SchedulerHelper,
                             disposable: CompositeDisposable) :
        BasePresenterImpl<V, I>(interactor = interactor, schedulerHelper = schedulerHelper, compositeDisposable = disposable),
        LoginInputPresenter<V, I> {

    override fun onLoginSubmitClicked(userName: String, secretKey: String, otpCode: String) {

        when {
            userName.isEmpty() -> getView()?.showLoginError(AppConstants.EMPTY_USERNAME_ERROR)
            secretKey.isEmpty() -> getView()?.showLoginError(AppConstants.EMPTY_SECRET_ERROR)
            else -> {
                interactor?.let {
                    compositeDisposable.add(
                                it.doServerBasicLoginApiCall(userName, secretKey, otpCode)
                                        .compose(schedulerHelper.ioToMainObservableScheduler())
                                        .subscribe({ response ->
                                            updateLoginInfoInPreference(userName, response, AppConstants.LoginMethod.BASIC)
                                            getView()?.startMainActivity()
                                        }, {
                                            if (it is ANError) {
                                                if (it.errorCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                                    if (it.response.header(GitHubApiConstants.OTP_HEADER, "")?.isNotEmpty() == true) {
                                                        getView()?.showTwoFactorInput()
                                                    }
                                                    getView()?.showLoginError(AppConstants.LOGIN_FAILURE_ERROR)
                                                } else {
                                                    this.onError()
                                                }
                                            } else {
                                                this.onError()
                                            }
                                        })
                    )
                }
            }
        }

    }

    private fun updateLoginInfoInPreference(userName: String, loginResponse: LoginResponse, loginMethod: AppConstants.LoginMethod) =
            interactor?.updateLoginInfoInPreference(userName, loginResponse, loginMethod)

}