package xyz.laziness.dailycommit.ui.base.presenter

import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.view.BaseView
import xyz.laziness.dailycommit.utils.AppConstants
import javax.inject.Inject


abstract class BasePresenter<V: BaseView> : BaseContract<V> {

    private var view: V? = null
    private val isViewAttached: Boolean
        get() = view != null

    @Inject
    private lateinit var appPreference: BasePreference

    override fun onAttach(view: V?) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }

    override fun getView(): V? = view

    override fun isLogin(): Boolean = this.appPreference.getCurrentLoginState() != AppConstants.LoginState.LOGOUT.state

    override fun logout() = this.appPreference.let {
        it.setCurrentUserId(null)
        it.setCurrentLoginState(AppConstants.LoginState.LOGOUT)
    }
}