package xyz.laziness.dailycommit.data.network.github

import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.github.florent37.rxjsoup.RxJsoup
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import okhttp3.Credentials
import okhttp3.OkHttpClient
import org.jsoup.Connection
import org.jsoup.Jsoup
import xyz.laziness.dailycommit.data.network.github.request.LoginRequest
import xyz.laziness.dailycommit.data.network.github.response.LoginResponse
import xyz.laziness.dailycommit.data.network.github.response.UserInfoResponse
import javax.inject.Inject


class GitHubApiHelper @Inject constructor() : GitHubApi {

    override fun doServerBasicLoginApiCall(userName: String, password: String): Observable<LoginResponse> =
            Rx2AndroidNetworking.post(GitHubApiConstants.REST_LOGIN_URL)
                    .addApplicationJsonBody(LoginRequest.BasicLoginRequest())
                    .setOkHttpClient(OkHttpClient.Builder()
                            .addInterceptor(GitHubInterceptor(Credentials.basic(userName, password)))
                            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .build()
                    .getObjectObservable(LoginResponse::class.java)

    override fun doUserInfoApiCall(token: String): Observable<UserInfoResponse> =
            Rx2AndroidNetworking.get(GitHubApiConstants.USER_INFO_URL)
                    .addHeaders("Authorization", "token $token")
                    .setOkHttpClient(OkHttpClient.Builder()
                            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                            .build())
                    .build()
                    .getObjectObservable(UserInfoResponse::class.java)

    override fun doContributionRequest(userName: String): Observable<Connection.Response> =
            RxJsoup.connect(
                    Jsoup.connect(GitHubApiConstants.CONTRIBUTION_URL.format(userName))
                            .method(Connection.Method.GET)
            )
}