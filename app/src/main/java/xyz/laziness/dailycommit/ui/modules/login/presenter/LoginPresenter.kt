package xyz.laziness.dailycommit.ui.modules.login.presenter

import android.content.Intent
import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.login.interactor.LoginInteractor
import xyz.laziness.dailycommit.ui.modules.login.view.LoginView


interface LoginPresenter<V: LoginView, I: LoginInteractor> : BasePresenter<V, I> {

    fun setLoginMainDisplay(): Unit?

    fun onHandleOauthIntent(intent: Intent?)

}