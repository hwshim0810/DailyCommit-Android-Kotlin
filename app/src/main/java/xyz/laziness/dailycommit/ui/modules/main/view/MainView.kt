package xyz.laziness.dailycommit.ui.modules.main.view

import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.view.BaseView


interface MainView : BaseView {

    fun startLoginActivity()

    fun openUserStatusFragment()

    fun setUpDrawer(userInfoResponse: UserInfoResponse)

}