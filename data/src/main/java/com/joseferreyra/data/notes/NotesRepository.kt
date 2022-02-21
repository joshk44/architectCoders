package com.joseferreyra.data.notes

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.domain.Note

interface NotesRepository {

    fun requestNotes(): Either<Failure, List<Note>>

    class NotesRepositoryImpl(private val localDataSource: NotesLocalDataSource,
                                  private val remoteDataSource: NotesRemoteDataSource): NotesRepository {
        override fun requestNotes(): Either<Failure, List<Note>> {
            return remoteDataSource.requestNotes()

            /*
            I'm not implementing local cache using Room
            In that case I should implemnt something like this:

            if (shouldSynchronize ()) {
             remoteDataSource.requestNotes() -> store on Room
            }

            return localDataSource.requestNotes()

             */

        }
    }
}