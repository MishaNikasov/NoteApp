package com.app.noteapp.view.screen.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.noteapp.view.screen.note.model.NoteScreenEvent
import com.app.noteapp.view.screen.note.model.NoteViewState
import com.app.util.EventHandler
import com.app.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(), EventHandler<NoteScreenEvent> {

    private val _noteViewState: MutableStateFlow<ViewState<NoteViewState>> = MutableStateFlow(ViewState.loading())
    val noteViewState: StateFlow<ViewState<NoteViewState>> = _noteViewState

    override fun obtainEvent(event: NoteScreenEvent) {
        when (event) {
            is NoteScreenEvent.HandleNoteState -> handleNoteState(event.noteId)
            is NoteScreenEvent.CreateNote -> createNote(event.title, event.text)
            is NoteScreenEvent.UpdateNote -> updateNote(event.title, event.text, event.note)
            is NoteScreenEvent.DeleteNote -> deleteNote(event.note)
        }
    }

    private fun handleNoteState(noteId: Long?) {
        if (noteId != null) {
            loadNote(noteId)
        } else {
            _noteViewState.value = ViewState.successes(NoteViewState.Create)
        }
    }

    private fun loadNote(noteId: Long) {
        _noteViewState.value = ViewState.loading()
        viewModelScope.launch {
            val note = noteRepository.getNote(noteId)
            _noteViewState.value = ViewState.successes(NoteViewState.Edit(note))
        }
    }

    private fun createNote(title: String, text: String) {
        _noteViewState.value = ViewState.loading()
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                    id = 0,
                    title = title,
                    text = text,
                    createDate = Date()
                )
            )
            _noteViewState.value = ViewState.successes(NoteViewState.NoteUpdated)
        }
    }

    private fun updateNote(title: String, text: String, note: Note) {
        _noteViewState.value = ViewState.loading()
        viewModelScope.launch {
            noteRepository.updateNote(
                note.copy(
                    title = title,
                    text = text
                )
            )
            _noteViewState.value = ViewState.successes(NoteViewState.NoteUpdated)
        }
    }

    private fun deleteNote(note: Note) {
        _noteViewState.value = ViewState.loading()
        viewModelScope.launch {
            noteRepository.deleteNote(note.id)
            _noteViewState.value = ViewState.successes(NoteViewState.NoteUpdated)
        }
    }

}