package com.app.noteapp.view.screen.note.model

import com.app.domain.model.Note
import com.app.util.ScreenEvent

sealed class NoteScreenEvent: ScreenEvent {
    data class HandleNoteState(val noteId: Long?): NoteScreenEvent()
    data class CreateNote(val title: String, val text: String): NoteScreenEvent()
    data class DeleteNote(val note: Note): NoteScreenEvent()
    data class UpdateNote(val title: String, val text: String, val note: Note): NoteScreenEvent()
}