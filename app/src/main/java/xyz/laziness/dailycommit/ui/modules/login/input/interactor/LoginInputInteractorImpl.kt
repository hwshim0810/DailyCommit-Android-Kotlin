package xyz.laziness.dailycommit.ui.modules.login.input.interactor

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


class LoginInputInteractorImpl
@Inject internal constructor(appPreference: BasePreference, apiHelper: GitHubApi): BaseInteractorImpl(appPreference, apiHelper), LoginInputInteractor {

    override fun doServerBasicLoginApiCall(userName: String, password: String, otpCode: String): Observable<LoginResponse> =
        apiHelper.doServerBasicLoginApiCall(userName, password, otpCode)


    override fun updateLoginInfoInPreference(userName: String, loginResponse: LoginResponse, loginMethod: AppConstants.LoginMethod) =
            appPreference.let {
                it.setCurrentUserName(userName)
                it.setCurrentUserToken(loginResponse.token)
                it.setCurrentLoginMethod(loginMethod)
                it.setCurrentLoginState(AppConstants.LoginState.LOGIN_GITHUB)
            }
}