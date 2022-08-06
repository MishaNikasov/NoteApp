package com.app.noteapp.view.screen.note.model

import com.app.domain.model.Note

sealed class NoteViewState {
    object NoteUpdated : NoteViewState()
    object Create : NoteViewState()
    data class Edit(val note: Note) : NoteViewState()
}