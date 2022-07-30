package com.app.noteapp.view.screen.note.model

import com.app.domain.model.Note

sealed class NoteViewState {

    object Loading: NoteViewState()
    object Create: NoteViewState()
    object NoteUpdated: NoteViewState()
    data class Edit(val note: Note): NoteViewState()

}