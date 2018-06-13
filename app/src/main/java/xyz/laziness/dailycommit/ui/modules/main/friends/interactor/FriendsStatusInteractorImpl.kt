package xyz.laziness.dailycommit.ui.modules.main.friends.interactor

import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import javax.inject.Inject


class FriendsStatusInteractorImpl
@Inject internal constructor(appPreference: BasePreference, apiHelper: GitHubApi): BaseInteractorImpl(appPreference, apiHelper), FriendsStatusInteractor {

}