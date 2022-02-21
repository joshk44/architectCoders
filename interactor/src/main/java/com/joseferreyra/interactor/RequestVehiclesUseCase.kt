package com.joseferreyra.interactor.main

import com.joseferreyra.interactor.base.UseCase
import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.domain.Vehicle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class RequestVehiclesUseCase(private val vehicleRepository: VehicleRepository,
                              scope: CoroutineScope,
                              dispatcher: CoroutineDispatcher
): UseCase<List<Vehicle>, Unit>(scope, dispatcher) {

    override suspend fun run(params: Unit): Either<Failure, List<Vehicle>> {
       return vehicleRepository.requestVehicles ()
    }
}