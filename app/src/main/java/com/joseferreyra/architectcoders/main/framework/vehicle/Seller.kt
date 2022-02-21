package com.joseferreyra.architectcoders.main.framework.vehicle


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Seller(
    @SerializedName("type") val type: String? = null,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("city") val city: String? = null
)