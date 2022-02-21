package com.joseferreyra.interactor

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.domain.Vehicle
import com.joseferreyra.interactor.main.RequestVehiclesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Test

class RequestVehicleUseCaseTest {

    private val repository: VehicleRepository.VehicleRepositoryImpl = mock()

    private lateinit var requestVehiclesUseCase: RequestVehiclesUseCase

    private lateinit var coroutineScope: CoroutineScope


    @Before
    fun setup() {
        coroutineScope = TestCoroutineScope()
        requestVehiclesUseCase =
            RequestVehiclesUseCase(repository, coroutineScope, Dispatchers.Unconfined)

    }

    @Test
    fun `Repository should be call with params`() {
        requestVehiclesUseCase.invoke(Unit, {})
        verify(repository).requestVehicles()
    }

    @Test
    fun `Repository should response value`() {
        val response: (List<Vehicle>) -> Unit = mock()
        whenever(repository.requestVehicles()).thenReturn(Either.Right(mockedVehicles))
        requestVehiclesUseCase.invoke(Unit) { it.either({}, response) }
        verify(response).invoke(mockedVehicles)
    }

    @Test
    fun `Repository should response failure`() {
        val response: (Failure) -> Unit = mock()
        whenever(repository.requestVehicles()).thenReturn(Either.Left(Failure.FeatureFailure))
        requestVehiclesUseCase.invoke(Unit) { it.either(response, {}) }
        verify(response).invoke(Failure.FeatureFailure)
    }
}


val mockedVehicles = listOf<Vehicle>( Vehicle(1,
    "BMW" ,
    "316i",
    13000,
    "Gasoline",
    listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw")),
    Vehicle(2,
        "BMW" ,
        "316i",
        13000,
        "Gasoline",
        listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw"))
)