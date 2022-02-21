package com.joseferreyra.architectcoders.main.framework.vehicle

import com.joseferreyra.architectcoders.extensions.request
import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.vehicle.VehicleRemoteDataSource
import com.joseferreyra.domain.Vehicle

class VehicleRemoteDataSourceImpl(private val service: VehicleService) : VehicleRemoteDataSource {

    override fun requestVehicles(): Either<Failure, List<Vehicle>> {
        return request(service.requestVehicles()) {
            it.map { it.parseToDomain() }
        }
    }
}