package xyz.laziness.dailycommit.ui.modules.login.interactor

import android.content.Context
import io.reactivex.Single
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.network.github.response.OauthTokenResponse
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


class LoginInteractorImpl
@Inject constructor(private val context: Context,
                    appPreference: BasePreference, apiHelper: GitHubApi) : BaseInteractorImpl(appPreference, apiHelper), LoginInteractor {


    override fun doOauthAccessTokenCall(code: String): Single<OauthTokenResponse> =
            apiHelper.doOauthAccessTokenCall(code)

    override fun updateLoginInfoInPreference(tokenResponse: OauthTokenResponse, loginMethod: AppConstants.LoginMethod) =
            appPreference.let {
                it.setCurrentUserToken(tokenResponse.token)
                it.setCurrentLoginMethod(loginMethod)
                it.setCurrentLoginState(AppConstants.LoginState.LOGIN_GITHUB)
            }
}