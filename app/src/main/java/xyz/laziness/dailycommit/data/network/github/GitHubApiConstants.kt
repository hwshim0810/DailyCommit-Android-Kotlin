package xyz.laziness.dailycommit.data.network.github

import xyz.laziness.dailycommit.BuildConfig


object GitHubApiConstants {

    const val CLIENT_ID = BuildConfig.GITHUB_APP_ID

    const val BASE_URL = "https://github.com"
    const val LOGIN_REDIRECT_URL = "dc://login"
    const val REST_URL = "https://api.github.com"

    const val OAUTH_LOGIN_URL = "$BASE_URL/login/oauth/access_token"
    const val REST_LOGIN_URL = "$REST_URL/authorizations"
    const val USER_INFO_URL = "$REST_URL/user"
    const val PUBLIC_USER_INFO_URL = "$REST_URL/users/%s"
    const val CONTRIBUTION_URL = "$BASE_URL/users/%s/contributions"

    const val REQUST_SCOPE = "user,repo"

    const val OTP_HEADER = "X-GitHub-OTP"
}