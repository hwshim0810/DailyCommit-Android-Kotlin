package xyz.laziness.dailycommit.data.network.github

import io.reactivex.Observable
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse


interface GitHubApi {

    fun doServerBasicLoginApiCall(userName: String, password: String): Observable<LoginResponse>

}