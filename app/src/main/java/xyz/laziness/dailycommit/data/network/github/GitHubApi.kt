package xyz.laziness.dailycommit.data.network.github

import io.reactivex.Observable
import io.reactivex.Single
import xyz.laziness.dailycommit.data.network.github.data.ContributionDay
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse


interface GitHubApi {

    fun doServerBasicLoginApiCall(userName: String, password: String): Observable<LoginResponse>

    fun doUserInfoApiCall(token: String): Observable<UserInfoResponse>

    fun doContributionRequest(userName: String): Single<List<ContributionDay>>

    fun doPublicUserInfoApiCall(userName: String): Observable<UserInfoResponse>

}