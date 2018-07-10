package xyz.laziness.dailycommit.ui.modules.login.input.view

import xyz.laziness.dailycommit.ui.base.view.BaseView


interface LoginInputView : BaseView {

    fun showLoginError(errCode: Int)

    fun startMainActivity()

    fun showTwoFactorInput()

}