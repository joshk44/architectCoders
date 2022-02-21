package com.joseferreyra.architectcoders.main.framework.note

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NotesResponseItem(
    @SerializedName("vehicleId") val vehicleId: Int? = null,
    @SerializedName("note") val note: String? = null
)