package com.joseferreyra.architectcoders.main.framework.note

import com.joseferreyra.architectcoders.extensions.request
import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.notes.NotesRemoteDataSource
import com.joseferreyra.domain.Note

class NotesRemoteDataSourceImpl(val service: NotesService) : NotesRemoteDataSource {

    override fun requestNotes(): Either<Failure, List<Note>> {
        return request(service.requestNotes()) {
            it.map { it.parseToDomain() }
        }
    }

}