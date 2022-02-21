package com.joseferreyra.architectcoders.main.framework.vehicle

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.vehicle.VehicleLocalDataSource
import com.joseferreyra.domain.Vehicle

class VehicleLocalDataSourceImpl() : VehicleLocalDataSource {

    override fun requestVehicles(): Either<Failure, List<Vehicle>> {
        TODO("Not yet implemented")
    }

}