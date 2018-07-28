package xyz.laziness.dailycommit.ui.modules.main.user.interactor

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import javax.inject.Inject


class UserStatusInteractorImpl
@Inject internal constructor(appPreference: BasePreference, apiHelper: GitHubApi) : BaseInteractorImpl(appPreference, apiHelper), UserStatusInteractor {

    override fun doUserInfoApiCall(): Observable<UserInfoResponse> =
            apiHelper.doUserInfoApiCall(appPreference.getCurrentUserToken() ?: "")

    override fun updateUserName(userInfoResponse: UserInfoResponse): Boolean {
        val needUpdate = appPreference.getCurrentUserName().isEmpty()

        if (needUpdate)
           appPreference.setCurrentUserName(userInfoResponse.userId)

        return needUpdate
    }
}