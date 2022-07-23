package com.app.noteapp.view.screen.note

import com.app.domain.model.Note

sealed class NoteScreenState {

    object Loading: NoteScreenState()
    object Create: NoteScreenState()
    object NoteUpdated: NoteScreenState()
    data class Edit(val note: Note): NoteScreenState()

    val btnText: String
        get() {
            return when(this) {
                Create -> "Create"
                is Edit -> "Edit"
                else -> ""
            }
        }
}