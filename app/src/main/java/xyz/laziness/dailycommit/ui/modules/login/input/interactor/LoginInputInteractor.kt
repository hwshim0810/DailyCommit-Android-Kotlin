package xyz.laziness.dailycommit.ui.modules.login.input.interactor

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.utils.AppConstants


interface LoginInputInteractor : BaseInteractor {

    fun doServerBasicLoginApiCall(userName: String, password: String, otpCode: String): Observable<LoginResponse>

    fun updateLoginInfoInPreference(userName: String, loginResponse: LoginResponse, loginMethod: AppConstants.LoginMethod)

}