package xyz.laziness.dailycommit.ui.modules.main.interactor

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.database.repository.friends.Friend
import xyz.laziness.dailycommit.data.database.repository.friends.FriendRepo
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


class MainInteractorImpl
@Inject internal constructor(private val friendRepo: FriendRepo,
                             appPreference: BasePreference,
                             apiHelper: GitHubApi) : BaseInteractorImpl(appPreference, apiHelper), MainInteractor {

    override fun doUserInfoApiCall(): Observable<UserInfoResponse> =
            apiHelper.doUserInfoApiCall(appPreference.getCurrentUserToken() ?: "")

    override fun removeUserInfoInPreference() {
        appPreference.let {
            it.setCurrentUserToken(null)
            it.setCurrentLoginState(AppConstants.LoginState.LOGOUT)
        }
    }

    override fun doPublicUserInfoApiCall(friendName: String): Observable<Boolean> {
        if (friendName == appPreference.getCurrentUserName())
            return Observable.just(false)

        return apiHelper.doPublicUserInfoApiCall(friendName)
                .concatMap {
                    friendRepo.insertFriend(Friend(0, friendName, appPreference.getCurrentUserName()))
                }
    }
}