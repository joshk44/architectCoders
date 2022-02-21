package com.joseferreyra.architectcoders

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.joseferreyra.architectcoders.main.ui.MainViewModel
import com.joseferreyra.architectcoders.main.ui.VehicleUI
import com.joseferreyra.architectcoders.main.ui.parseVehicleUI
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.domain.Note
import com.joseferreyra.domain.Vehicle
import com.joseferreyra.interactor.main.RequestNotesUseCase
import com.joseferreyra.interactor.main.RequestVehiclesUseCase
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var coroutineScope: CoroutineScope

    private lateinit var requestVehiclesUseCase: RequestVehiclesUseCase

    private var requestNotesUseCase: RequestNotesUseCase = mock()

    private lateinit var mainViewModel: MainViewModel

    private var vehicleRepository: VehicleRepository = mock()

    var observer: Observer<List<VehicleUI>> = mock()

    var observerFailure: Observer<Boolean> = mock()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        coroutineScope = TestCoroutineScope()
        requestVehiclesUseCase = RequestVehiclesUseCase (vehicleRepository, TestCoroutineScope(), Dispatchers.Unconfined)
        mainViewModel = MainViewModel(requestVehiclesUseCase, requestNotesUseCase)
    }

    @Test
    fun `View model vehicles is empty`() {
        Assert.assertNull(mainViewModel.vehicles.value)
    }

    @Test
    fun `View model call use case request Notes after requestVehicles`() {
        runBlocking {
            mainViewModel.success(emptyList())
            verify(requestNotesUseCase).invoke(any(), any())
        }
    }

    @Test
    fun `View model call use case on requestVehicles failure`() {
        runBlocking {
            whenever(requestVehiclesUseCase(Unit)).then {mainViewModel.failure(Failure.FeatureFailure)}
            mainViewModel.onRequestError.observeForever(observerFailure)
            mainViewModel.requestVehicles()
            verify(observerFailure).onChanged(true)
        }
    }

    @Test
    fun `View model call use case on requestVehicles ok requestNotes failure`() {
        runBlocking {
            mainViewModel.success(mockedVehicles)
            whenever(requestVehiclesUseCase(Unit)).then {mainViewModel.failureNotes(Failure.FeatureFailure)}
            mainViewModel.vehicles.observeForever(observer)
            mainViewModel.requestVehicles()
            verify(observer).onChanged(mockedVehicles.map { it.parseVehicleUI() })
        }
    }

    @Test
    fun `View model call use case on requestVehicles ok requestNotes ok`() {
        runBlocking {
            mainViewModel.success(mockedVehicles)
            whenever(requestVehiclesUseCase(Unit)).then {mainViewModel.successNotes(mockedNotes)}
            mainViewModel.vehicles.observeForever(observer)
            mainViewModel.requestVehicles()
            verify(observer).onChanged(mockedVehicleUI)
        }
    }

    @Test
    fun `View model call use case on requestVehicles ok requestNotes incorrect`() {
        runBlocking {
            mainViewModel.success(mockedVehicles)
            whenever(requestVehiclesUseCase(Unit)).then {mainViewModel.successNotes(mockedNotesSecond)}
            mainViewModel.vehicles.observeForever(observer)
            mainViewModel.requestVehicles()
            verify(observer).onChanged(mockedVehicleUISecondNote)
        }
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
        listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw")))


val mockedVehicleUI = listOf<VehicleUI>( VehicleUI(1,
    "BMW 316i" ,
    13000,
    "Gasoline",
    listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw"),
    listOf("good note")),
    VehicleUI(2,
        "BMW 316i" ,
        13000,
        "Gasoline",
        listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw"),
        emptyList())
    )

val mockedVehicleUISecondNote = listOf<VehicleUI>( VehicleUI(1,
    "BMW 316i" ,
    13000,
    "Gasoline",
    listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw"),
    emptyList()),
    VehicleUI(2,
        "BMW 316i" ,
        13000,
        "Gasoline",
        listOf("https://loremflickr.com/g/320/240/bmw", "https://loremflickr.com/g/640/480/bmw"),
        listOf("good note"))
)


val mockedNotes = listOf<Note>( Note ( 1, "good note"))

val mockedNotesSecond = listOf<Note>( Note ( 2, "good note"))