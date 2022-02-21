package com.joseferreyra.architectcoders.main.ui

import com.joseferreyra.domain.Note
import com.joseferreyra.domain.Vehicle


data class VehicleUI(
    val id: Int? = null,
    val title: String = "-",
    val price: Int = 0,
    val fuel: String = "Unknown",
    val images: List<String> = emptyList(),
    val notes: List<String> = emptyList()
)

fun Vehicle.parseVehicleUI(notes: List<Note> = emptyList()) = VehicleUI(
    id,
    "$make $model",
    price ?: 0,
    fuel ?: "Unknown",
    this.images ?: emptyList(),
    notes.map { it.text })