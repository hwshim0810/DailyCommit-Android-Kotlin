package xyz.laziness.dailycommit.ui.modules.main.friends.interactor

import io.reactivex.Observable
import io.reactivex.Single
import xyz.laziness.dailycommit.data.database.repository.friends.Friend
import xyz.laziness.dailycommit.data.database.repository.friends.FriendRepo
import xyz.laziness.dailycommit.data.network.github.GitHubApi
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.data.parser.ContributionParser
import xyz.laziness.dailycommit.data.preference.BasePreference
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractorImpl
import xyz.laziness.dailycommit.utils.Colors
import javax.inject.Inject


class FriendsStatusInteractorImpl
@Inject internal constructor(private val friendRepo: FriendRepo,
                             appPreference: BasePreference,
                             apiHelper: GitHubApi): BaseInteractorImpl(appPreference, apiHelper), FriendsStatusInteractor {

    private val baseBlockColor = Colors.baseColors[appPreference.getBaseBlockColor()]

    override fun doMyContributionRequest(): Single<List<ContributionDay>> {
        ContributionParser.setBaseBlockColor(baseBlockColor)
        return doContributionRequest(appPreference.getCurrentUserName())
    }

    override fun doContributionRequest(userName: String): Single<List<ContributionDay>> =
        apiHelper.doContributionRequest(userName)
                .flatMapIterable { it.parse().select(ContributionParser.targetElement) }
                .filter { ContributionParser.hasAttributes(it) }
                .map { ContributionParser.parse(it) }
                .toList()

    override fun loadFriends(): Observable<List<Friend>> =
            friendRepo.loadFriends(appPreference.getCurrentUserName())

    override fun loadFriendByName(friendName: String): Observable<Friend> =
            friendRepo.loadFriendByName(appPreference.getCurrentUserName(), friendName)

    override fun deleteFriend(friend: Friend): Observable<Boolean> =
            friendRepo.deleteFriend(friend)
}