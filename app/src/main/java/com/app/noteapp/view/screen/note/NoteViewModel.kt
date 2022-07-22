package com.app.noteapp.view.screen.note

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.app.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(): ViewModel() {

    var noteScreenState: MutableState<NoteScreenState> = mutableStateOf(NoteScreenState.Loading)

    private fun loadNote(noteId: String) {
        noteScreenState.value = NoteScreenState.Loading
        viewModelScope.launch {
            delay(1000)
            noteScreenState.value = NoteScreenState.Edit(
                Note(
                    id = "123",
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

        }
    }

    fun updateNote(title: String, text: String, id: String) {
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