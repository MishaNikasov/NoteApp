package com.app.noteapp.view.screen.note

import com.app.domain.model.Note

sealed class NoteScreenEvent {
    data class HandleNoteState(val noteId: Long?): NoteScreenEvent()
    data class CreateNote(val title: String, val text: String): NoteScreenEvent()
    data class DeleteNote(val note: Note): NoteScreenEvent()
    data class UpdateNote(val title: String, val text: String, val note: Note): NoteScreenEvent()
}