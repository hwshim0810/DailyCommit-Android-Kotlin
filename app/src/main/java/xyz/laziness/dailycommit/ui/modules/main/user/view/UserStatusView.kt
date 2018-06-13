package xyz.laziness.dailycommit.ui.modules.main.user.view

import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import xyz.laziness.dailycommit.ui.base.view.BaseView


interface UserStatusView : BaseView {

    fun setUiData(userInfoResponse: UserInfoResponse)

}