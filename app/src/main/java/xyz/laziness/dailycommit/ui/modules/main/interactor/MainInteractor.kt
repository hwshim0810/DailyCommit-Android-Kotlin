package xyz.laziness.dailycommit.ui.modules.main.interactor

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor


interface MainInteractor : BaseInteractor {

    fun doUserInfoApiCall(): Observable<UserInfoResponse>

    fun removeUserInfoInPreference()

}