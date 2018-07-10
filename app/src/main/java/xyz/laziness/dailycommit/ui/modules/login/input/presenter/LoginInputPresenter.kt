package xyz.laziness.dailycommit.ui.modules.login.input.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.login.input.interactor.LoginInputInteractor
import xyz.laziness.dailycommit.ui.modules.login.input.view.LoginInputView


interface LoginInputPresenter<V: LoginInputView, I: LoginInputInteractor> : BasePresenter<V, I> {

    fun onLoginSubmitClicked(userName: String, secretKey: String, otpCode: String)

}