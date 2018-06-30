package xyz.laziness.dailycommit.data.network.github.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    @Expose
    @SerializedName("message") val message: String,

    @Expose
    @SerializedName("documentation_url") val documentation_url: String
)