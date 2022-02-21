package com.joseferreyra.architectcoders.main.framework.vehicle


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Image(
    @SerializedName("url") val url: String? = null
)