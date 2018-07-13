package xyz.laziness.dailycommit.data.network.github.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class OauthTokenResponse(

    @Expose
    @SerializedName("access_token") var token: String

)