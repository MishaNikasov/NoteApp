package com.app.noteapp.view.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.storage.AppStorage
import com.app.domain.model.ListArrangement
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.noteapp.view.screen.home.model.HomeScreenEvent
import com.app.noteapp.view.screen.home.model.HomeViewState
import com.app.util.EventHandler
import com.app.util.Result
import com.app.util.invalidEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val appStorage: AppStorage
) : ViewModel(), EventHandler<HomeScreenEvent> {

    private val _homeViewState: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Loading)
    val homeViewState: State<HomeViewState> = _homeViewState

    override fun obtainEvent(event: HomeScreenEvent) {
        when(val state = _homeViewState.value) {
            is HomeViewState.Loading -> reduce(state, event)
            is HomeViewState.EmptyContent -> reduce(state, event)
            is HomeViewState.NoteListContent -> reduce(state, event)
        }
    }

    private fun reduce(state: HomeViewState.Loading, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            else -> invalidEvent(event, state)
        }
    }

    private fun reduce(state: HomeViewState.EmptyContent, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            is HomeScreenEvent.ChangeListArrangement -> updateListArrangement(event.listArrangement)
            else -> invalidEvent(event, state)
        }
    }

    private fun reduce(state: HomeViewState.NoteListContent, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            is HomeScreenEvent.SearchNotes -> searchNotes(event.query, state.listArrangement)
            is HomeScreenEvent.ChangeListArrangement -> updateListArrangement(event.listArrangement)
        }
    }

    private fun searchNotes(query: String, listArrangement: ListArrangement) {
        viewModelScope.launch {
            getNotes { list ->
                val resultList = list.filter { it.text.contains(query) || it.title.contains(query) }
                _homeViewState.value = HomeViewState.NoteListContent(resultList, listArrangement)
            }
        }
    }

    private fun fetchNoteList() {
        viewModelScope.launch {
            _homeViewState.value = HomeViewState.Loading
            val listArrangement = appStorage.getListArrangement()
            getNotes { list ->
                if (list.isEmpty()) {
                    _homeViewState.value = HomeViewState.EmptyContent(listArrangement)
                } else {
                    _homeViewState.value = HomeViewState.NoteListContent(list, listArrangement)
                }
            }
        }
    }

    private suspend fun getNotes(result: (List<Note>) -> Unit) {
        noteRepository.getNoteList().getResult(
            successes = { list -> result(list ?: emptyList()) },
            error = { error -> Result.error(error) }
        )
    }

    private fun updateListArrangement(listArrangement: ListArrangement) {
        viewModelScope.launch {
            appStorage.updateListArrangement(listArrangement)
        }
    }

}