package com.joseferreyra.data.vehicle

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.domain.Vehicle

interface VehicleRepository {

    fun requestVehicles(): Either<Failure, List<Vehicle>>

    class VehicleRepositoryImpl(private val localDataSource: VehicleLocalDataSource,
                                  private val remoteDataSource: VehicleRemoteDataSource): VehicleRepository {
        override fun requestVehicles(): Either<Failure, List<Vehicle>> {
            return remoteDataSource.requestVehicles()

            /*
            I'm not implementing local cache using Room
            In that case I should implemnt something like this:

            if (shouldSynchronize ()) {
             remoteDataSource.requestVehicles() -> store on Room
            }

            return localDataSource.requestVehicles()

             */
        }
    }
}