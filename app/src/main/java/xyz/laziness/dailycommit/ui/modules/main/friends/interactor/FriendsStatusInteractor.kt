package xyz.laziness.dailycommit.ui.modules.main.friends.interactor

import io.reactivex.Single
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor
import xyz.laziness.dailycommit.ui.custom.GitHubGraphView


interface FriendsStatusInteractor : BaseInteractor {

    fun doMyContributionRequest(contributionView: GitHubGraphView): Single<List<ContributionDay>>

}