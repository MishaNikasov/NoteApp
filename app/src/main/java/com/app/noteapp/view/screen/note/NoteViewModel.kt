package com.app.noteapp.view.screen.note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    var noteScreenState: MutableState<NoteScreenState> = mutableStateOf(NoteScreenState.Loading)

    private fun loadNote(noteId: Long) {
        noteScreenState.value = NoteScreenState.Loading
        viewModelScope.launch {
            val note = noteRepository.getNote(noteId)
            noteScreenState.value = NoteScreenState.Edit(note)
        }
    }

    fun createNote(title: String, text: String) {
        noteScreenState.value = NoteScreenState.Loading
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                    id = 0,
                    title = title,
                    text = text,
                    createDate = Date()
                )
            )
            noteScreenState.value = NoteScreenState.NoteUpdated
        }
    }

    fun updateNote(title: String, text: String, note: Note) {
        noteScreenState.value = NoteScreenState.Loading
        viewModelScope.launch {
            delay(1400)
            noteRepository.updateNote(
                note.copy(
                    title = title,
                    text = text
                )
            )
            noteScreenState.value = NoteScreenState.NoteUpdated
        }
    }

    fun handleNoteState(noteId: Long?) {
        if(noteId != null) {
            loadNote(noteId)
        } else {
            noteScreenState.value = NoteScreenState.Create
        }
//        when(noteId) {
//            NoteScreenType.Create -> { noteScreenState.value = NoteScreenState.Create }
//            is NoteScreenType.Edit -> { loadNote(noteScreenType.noteId) }
//        }
    }

}