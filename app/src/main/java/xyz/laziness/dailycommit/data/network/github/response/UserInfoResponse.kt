package xyz.laziness.dailycommit.data.network.github.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserInfoResponse(
    @Expose
    @SerializedName("login") var userId: String,

    @Expose
    @SerializedName("avatar_url") var avatarUrl: String,

    @Expose
    @SerializedName("name") var userName: String,

    @Expose
    @SerializedName("bio") var bio: String,

    @Expose
    @SerializedName("location") var location: String
): Serializable