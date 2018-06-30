package xyz.laziness.dailycommit.ui.modules.main.view

import android.support.annotation.StringRes
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.view.BaseView


interface MainView : BaseView {

    fun startLoginActivity()

    fun openUserStatusFragment()

    fun openFriendsStatusFragment()

    fun setViewData(userInfoResponse: UserInfoResponse)

    fun lockDrawer(): Unit?

    fun unlockDrawer(): Unit?

    fun onResponseAddingFriend(friendName: String)

    fun showToastMessage(@StringRes message: Int)

    fun showToastMessage(message: String)

}