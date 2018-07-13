package xyz.laziness.dailycommit.data.network.github.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @Expose
    @SerializedName("id") var id: Long,

    @Expose
    @SerializedName("token") var token: String,

    @Expose
    @SerializedName("hashed_token") var hashedToken: String,

    @Expose
    @SerializedName("scopes") var scopes: List<String>
)