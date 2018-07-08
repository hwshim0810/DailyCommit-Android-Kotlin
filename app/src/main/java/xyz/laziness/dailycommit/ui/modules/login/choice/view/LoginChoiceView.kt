package xyz.laziness.dailycommit.ui.modules.login.choice.view

import android.support.customtabs.CustomTabsIntent
import xyz.laziness.dailycommit.ui.base.view.BaseView
import xyz.laziness.dailycommit.utils.AppConstants


interface LoginChoiceView : BaseView {

    fun openLoginInputFragment(loginMethod: AppConstants.LoginMethod)

    fun openGithubLoginWebView(customTabsIntent: CustomTabsIntent)

}