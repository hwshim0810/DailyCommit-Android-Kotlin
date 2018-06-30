package xyz.laziness.dailycommit.ui.modules.main.friends.view

import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.ui.base.view.BaseView


interface FriendsStatusView : BaseView {

    fun displayMyContributions(contributions: List<ContributionDay>): Unit?

    fun displayFriendContributions(contributions: List<ContributionDay>, friendName: String): Unit?

}