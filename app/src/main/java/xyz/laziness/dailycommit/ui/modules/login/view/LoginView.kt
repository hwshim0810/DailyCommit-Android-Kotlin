package xyz.laziness.dailycommit.ui.modules.login.view

import xyz.laziness.dailycommit.ui.base.view.BaseView
import xyz.laziness.dailycommit.utils.AppConstants


interface LoginView : BaseView {

    fun openLoginChooseFragment()

    fun openBasicLoginFragment()

    fun openTokenLoginFragment()

    fun openWebLoginFragment()

    fun openLoginInputFragment(loginMethod: AppConstants.LoginMethod)

    fun startMainActivity()

    fun sendLoginBroadCast()

    fun showLoginError(errCode: Int)

}
