package xyz.laziness.dailycommit.data.network.github.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import xyz.laziness.dailycommit.BuildConfig


class LoginRequest {

    data class BasicLoginRequest internal constructor(
            @SerializedName("client_id") internal val clientId: String = BuildConfig.GITHUB_APP_ID,
            @SerializedName("client_secret") internal val clientSecret: String = BuildConfig.GITHUB_APP_SECRET,
            @Expose
            @SerializedName("scopes") internal val scopes: List<String> = listOf("user", "repo"),
            @Expose
            @SerializedName("note") internal val note: String = BuildConfig.APPLICATION_ID
    )

}