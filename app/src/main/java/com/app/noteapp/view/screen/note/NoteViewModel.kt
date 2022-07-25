package com.app.noteapp.view.screen.note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.util.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel(), EventHandler<NoteScreenEvent> {

    var noteViewState: MutableState<NoteViewState> = mutableStateOf(NoteViewState.Loading)

    override fun obtainEvent(event: NoteScreenEvent) {
        when(event) {
            is NoteScreenEvent.HandleNoteState -> handleNoteState(event.noteId)
            is NoteScreenEvent.CreateNote -> createNote(event.title, event.text)
            is NoteScreenEvent.UpdateNote -> updateNote(event.title, event.text, event.note)
            is NoteScreenEvent.DeleteNote -> deleteNote(event.note)
        }
    }

    private fun handleNoteState(noteId: Long?) {
        if(noteId != null) {
            loadNote(noteId)
        } else {
            noteViewState.value = NoteViewState.Create
        }
    }

    private fun loadNote(noteId: Long) {
        noteViewState.value = NoteViewState.Loading
        viewModelScope.launch {
            val note = noteRepository.getNote(noteId)
            noteViewState.value = NoteViewState.Edit(note)
        }
    }

    private fun createNote(title: String, text: String) {
        noteViewState.value = NoteViewState.Loading
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                    id = 0,
                    title = title,
                    text = text,
                    createDate = Date()
                )
            )
            noteViewState.value = NoteViewState.NoteUpdated
        }
    }

    private fun updateNote(title: String, text: String, note: Note) {
        noteViewState.value = NoteViewState.Loading
        viewModelScope.launch {
            noteRepository.updateNote(
                note.copy(
                    title = title,
                    text = text
                )
            )
            noteViewState.value = NoteViewState.NoteUpdated
        }
    }

    private fun deleteNote(note: Note) {
        noteViewState.value = NoteViewState.Loading
        viewModelScope.launch {
            noteRepository.deleteNote(note.id)
            noteViewState.value = NoteViewState.NoteUpdated
        }
    }

}