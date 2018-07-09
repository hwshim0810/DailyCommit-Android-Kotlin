package xyz.laziness.dailycommit.ui.modules.login.interactor

import io.reactivex.Single
import xyz.laziness.dailycommit.data.network.github.response.OauthTokenResponse
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.utils.AppConstants


interface LoginInteractor : BaseInteractor {

    fun doOauthAccessTokenCall(code: String): Single<OauthTokenResponse>

    fun updateLoginInfoInPreference(tokenResponse: OauthTokenResponse, loginMethod: AppConstants.LoginMethod)

}