package com.joseferreyra.interactor.main

import com.joseferreyra.interactor.base.UseCase
import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.notes.NotesRepository
import com.joseferreyra.data.vehicle.VehicleRepository
import com.joseferreyra.domain.Note
import com.joseferreyra.domain.Vehicle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class RequestNotesUseCase(private val notesRepository: NotesRepository,
                          scope: CoroutineScope,
                          dispatcher: CoroutineDispatcher
): UseCase<List<Note>, Unit>(scope, dispatcher) {

    override suspend fun run(params: Unit): Either<Failure, List<Note>> {
       return notesRepository.requestNotes ()
    }
}