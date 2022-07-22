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
            delay(1000)
            noteScreenState.value = NoteScreenState.Edit(
                Note(
                    id = 0,
                    title = "Title",
                    text = "Qwqe ewqeqw ewqe qwe wq eqw e qw eqw ew",
                    createDate = Date(),
                    editDate = Date()
                )
            )
        }
    }

    fun createNote(title: String, text: String) {
        viewModelScope.launch {
            noteRepository.insertNote(
                Note(
                    id = 0,
                    title = title,
                    text = text,
                    createDate = Date()
                )
            )
        }
    }

    fun updateNote(title: String, text: String, id: Long) {
        viewModelScope.launch {

        }
    }

    fun handleNoteState(noteScreenType: NoteScreenType) {
        when(noteScreenType) {
            NoteScreenType.Create -> { noteScreenState.value = NoteScreenState.Create }
            is NoteScreenType.Edit -> { loadNote(noteScreenType.noteId) }
        }
    }

}