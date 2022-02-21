package com.joseferreyra.architectcoders.main.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.domain.Note
import com.joseferreyra.domain.Vehicle
import com.joseferreyra.interactor.main.RequestNotesUseCase
import com.joseferreyra.interactor.main.RequestVehiclesUseCase

class MainViewModel(
    private val requestVehiclesUseCase: RequestVehiclesUseCase,
    private val requestNotesUseCase: RequestNotesUseCase
) : ViewModel() {

    private val _vehicles = MutableLiveData<List<VehicleUI>>()
    val vehicles: LiveData<List<VehicleUI>> get() = _vehicles

    private val _onRequestError = MutableLiveData<Boolean>(false)
    val onRequestError: LiveData<Boolean> get() = _onRequestError

    var vehiclesReceived = emptyList<Vehicle>()

    fun requestVehicles() {
        requestVehiclesUseCase(Unit) { it.either(::failure, ::success) }
    }

    fun failure(failure: Failure) {
        _onRequestError.value = true
        Log.d("MainViewModel", "failure : $failure")
    }

    fun success(vehiclesList: List<Vehicle>) {
        vehiclesReceived = vehiclesList
        requestNotesUseCase(Unit) { it.either(::failureNotes, ::successNotes) }
    }

    fun successNotes(list: List<Note>) {
        _vehicles.value = vehiclesReceived.map { vehicle ->
            vehicle.parseVehicleUI(list.filter { note ->
                note.vehicleId == vehicle.id
            })
        }
    }

    fun failureNotes(failure: Failure) {
        _vehicles.value = vehiclesReceived.map { it.parseVehicleUI() }
        Log.d("MainViewModel", "failure : $failure")
    }
}