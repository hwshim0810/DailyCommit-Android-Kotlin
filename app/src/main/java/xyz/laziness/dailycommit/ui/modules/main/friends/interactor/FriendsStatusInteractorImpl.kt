package xyz.laziness.dailycommit.ui.modules.main.friends.interactor

import io.reactivex.Single
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.data.parser.ContributionParser
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import xyz.laziness.dailycommit.ui.custom.GitHubGraphView
import xyz.laziness.dailycommit.utils.Colors
import javax.inject.Inject


class FriendsStatusInteractorImpl
@Inject internal constructor(appPreference: BasePreference, apiHelper: GitHubApi): BaseInteractorImpl(appPreference, apiHelper), FriendsStatusInteractor {

    private val baseBlockColor = Colors.baseColors[appPreference.getBaseBlockColor()]

    override fun doMyContributionRequest(contributionView: GitHubGraphView): Single<List<ContributionDay>> {
        contributionView.setBaseBlockColor(baseBlockColor)

        return appPreference.getCurrentUserName().run {
            apiHelper.doContributionRequest(this)
                    .flatMapIterable { it.parse().select(ContributionParser.targetElement) }
                    .filter { ContributionParser.hasAttributes(it) }
                    .map { ContributionParser.parse(it) }
                    .toList()
        }
    }


}