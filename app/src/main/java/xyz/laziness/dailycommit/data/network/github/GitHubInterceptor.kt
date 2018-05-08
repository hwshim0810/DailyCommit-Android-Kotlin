package xyz.laziness.dailycommit.data.network.github

import okhttp3.Interceptor
import okhttp3.Response


class GitHubInterceptor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val builder = chain!!.request().newBuilder()

        if (token.isNotBlank()) builder.header("Authorization", if (token.startsWith("Basic")) token else "token $token")

        return chain.proceed(builder.build())
    }

}