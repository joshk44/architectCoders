package com.joseferreyra.data.vehicle

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.domain.Vehicle

interface VehicleRemoteDataSource {
    fun requestVehicles(): Either<Failure, List<Vehicle>>
}