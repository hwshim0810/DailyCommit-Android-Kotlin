package xyz.laziness.dailycommit.ui.base.interactor

import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.utils.AppConstants


open class BaseInteractorImpl() : BaseInteractor {

    lateinit var appPreference: BasePreference
    lateinit var apiHelper: GitHubApi

    constructor(appPreference: BasePreference, apiHelper: GitHubApi) : this() {
        this.appPreference = appPreference
        this.apiHelper = apiHelper
    }

    constructor(appPreference: BasePreference) : this() {
        this.appPreference = appPreference
    }

    override fun isLogin(): Boolean = this.appPreference.getCurrentLoginState() != AppConstants.LoginState.LOGOUT.state

    override fun logout() = this.appPreference.let {
        it.setCurrentUserToken(null)
        it.setCurrentLoginState(AppConstants.LoginState.LOGOUT)
    }

}