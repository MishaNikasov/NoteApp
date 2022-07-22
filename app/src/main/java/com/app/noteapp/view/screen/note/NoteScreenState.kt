package com.app.noteapp.view.screen.note

import com.app.domain.model.Note

sealed class NoteScreenState {

    object Loading: NoteScreenState()
    object Create: NoteScreenState()
    data class Edit(val note: Note): NoteScreenState()

    val btnText: String
        get() {
            return when(this) {
                Create -> "Create"
                Loading -> ""
                is Edit -> "Edit"
            }
        }
}