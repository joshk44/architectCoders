package com.joseferreyra.architectcoders.main.framework.note

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.data.notes.NotesLocalDataSource
import com.joseferreyra.domain.Note

class NotesLocalDataSourceImpl() : NotesLocalDataSource {

    override fun requestNotes(): Either<Failure, List<Note>> {
        TODO("Not yet implemented")
    }
}