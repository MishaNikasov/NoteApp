package com.app.noteapp.view.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository
): ViewModel() {

    private val _noteList = MutableLiveData<State<List<Note>>>()
    val noteList: LiveData<State<List<Note>>> = _noteList

    fun fetchNoteList() {
        viewModelScope.launch {
            _noteList.postValue(State.loading())
            noteRepository.getNoteList().getResult(
                successes = { list -> _noteList.postValue(State.successes(list ?: emptyList())) },
                error = { error -> State.error(error) }
            )
        }
    }
}