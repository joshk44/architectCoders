package com.joseferreyra.architectcoders.main.framework.vehicle

import retrofit2.Call
import retrofit2.http.GET

interface VehicleService {

    @GET("https://private-fe87c-simpleclassifieds.apiary-mock.com/")
    fun requestVehicles(): Call<VehicleResponse>

}