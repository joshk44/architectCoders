package com.joseferreyra.architectcoders.main.framework.vehicle


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.joseferreyra.domain.Vehicle

@Keep
data class VehicleResponseItem(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("make") val make: String? = null,
    @SerializedName("model") val model: String? = null,
    @SerializedName("price") val price: Int? = null,
    @SerializedName("firstRegistration") val firstRegistration: String? = null,
    @SerializedName("mileage") val mileage: Int? = null,
    @SerializedName("fuel") val fuel: String? = null,
    @SerializedName("images") val images: List<Image>? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("modelline") val modelline: String? = null,
    @SerializedName("seller") val seller: Seller? = null,
    @SerializedName("colour") val colour: String? = null
)

fun VehicleResponseItem.parseToDomain() =
    Vehicle(this.id, this.make, this.model, this.price, this.fuel, this.images?.map { it.url ?: "" })