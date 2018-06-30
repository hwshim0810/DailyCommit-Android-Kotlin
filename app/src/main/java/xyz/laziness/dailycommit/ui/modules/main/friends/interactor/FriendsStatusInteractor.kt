package xyz.laziness.dailycommit.ui.modules.main.friends.interactor

import io.reactivex.Observable
import io.reactivex.Single
import xyz.laziness.dailycommit.data.database.repository.friends.Friend
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.ui.base.interactor.BaseInteractor


interface FriendsStatusInteractor : BaseInteractor {

    fun doMyContributionRequest(): Single<List<ContributionDay>>

    fun doContributionRequest(userName: String): Single<List<ContributionDay>>

    fun loadFriends(): Observable<List<Friend>>

}