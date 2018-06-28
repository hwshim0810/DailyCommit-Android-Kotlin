package xyz.laziness.dailycommit.data.network.github

import io.reactivex.Observable
import org.jsoup.Connection
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse


interface GitHubApi {

    fun doServerBasicLoginApiCall(userName: String, password: String): Observable<LoginResponse>

    fun doUserInfoApiCall(token: String): Observable<UserInfoResponse>

    fun doContributionRequest(userName: String): Observable<Connection.Response>

}