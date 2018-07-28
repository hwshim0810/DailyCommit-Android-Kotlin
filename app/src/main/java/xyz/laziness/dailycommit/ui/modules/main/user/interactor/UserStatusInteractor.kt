package xyz.laziness.dailycommit.ui.modules.main.user.interactor

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor


interface UserStatusInteractor : BaseInteractor {

    fun doUserInfoApiCall(): Observable<UserInfoResponse>

    fun updateUserName(userInfoResponse: UserInfoResponse): Boolean

}