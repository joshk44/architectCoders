package com.joseferreyra.domain

import androidx.annotation.Keep

@Keep
data class Vehicle(
    val id: Int? = null,
    val make: String? = null,
    val model: String? = null,
    val price: Int? = null,
    val fuel: String? = null,
    val images: List<String>? = null,
)