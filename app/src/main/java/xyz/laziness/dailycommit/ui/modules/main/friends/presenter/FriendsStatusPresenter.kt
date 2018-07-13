package xyz.laziness.dailycommit.ui.modules.main.friends.presenter

import xyz.laziness.dailycommit.ui.base.presenter.BasePresenter
import xyz.laziness.dailycommit.ui.modules.main.friends.interactor.FriendsStatusInteractor
import xyz.laziness.dailycommit.ui.modules.main.friends.view.FriendsStatusView


interface FriendsStatusPresenter<V: FriendsStatusView, I: FriendsStatusInteractor> : BasePresenter<V, I> {

    fun doMyContributionRequest()

    fun doFriendsContributionRequest()

    fun doFriendContributionRequest(friendName: String)

    fun onFriendDeleteButtonClicked(friendName: String, pos: Int)

}