package xyz.laziness.dailycommit.data.network.github


object GitHubApiConstants {

    const val BASE_URL = "https://github.com"
    const val LOGIN_BASE_URL = "$BASE_URL/login/outh"
    const val AUTHORIZE_URL = "$LOGIN_BASE_URL/authorize/"
    const val LOGIN_REDIRECT_URL = "dc://login"

    const val REST_URL = "https://api.github.com"
    const val REST_LOGIN_URL = "$REST_URL/authorizations"

    const val REQUST_SCOPE = "user repo"
}