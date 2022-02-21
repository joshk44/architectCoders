package com.joseferreyra.data.notes

import com.joseferreyra.common.utils.Either
import com.joseferreyra.common.utils.Failure
import com.joseferreyra.domain.Note

interface NotesRemoteDataSource {
    fun requestNotes(): Either<Failure, List<Note>>
}