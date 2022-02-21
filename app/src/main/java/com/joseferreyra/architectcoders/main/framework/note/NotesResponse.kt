package com.joseferreyra.architectcoders.main.framework.note

import com.joseferreyra.domain.Note

class NotesResponse : ArrayList<NotesResponseItem>()

fun NotesResponseItem.parseToDomain() = Note(this.vehicleId ?: 0, this.note ?: "")