package xyz.laziness.dailycommit.ui.base.interactor

import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


open class BaseInteractorImpl : BaseInteractor {

    @Inject
    internal lateinit var appPreference: BasePreference

    override fun isLogin(): Boolean = this.appPreference.getCurrentLoginState() != AppConstants.LoginState.LOGOUT.state

    override fun logout() = this.appPreference.let {
        it.setCurrentUserId(null)
        it.setCurrentLoginState(AppConstants.LoginState.LOGOUT)
    }

}