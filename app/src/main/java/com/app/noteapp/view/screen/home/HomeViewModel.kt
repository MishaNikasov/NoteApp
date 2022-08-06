package com.app.noteapp.view.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.storage.AppStorage
import com.app.domain.model.ListArrangement
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.noteapp.view.screen.home.model.HomeScreenEvent
import com.app.noteapp.view.screen.home.model.HomeViewState
import com.app.util.EventHandler
import com.app.util.ViewState
import com.app.util.invalidEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val appStorage: AppStorage
) : ViewModel(), EventHandler<HomeScreenEvent> {

    private val _homeViewState: MutableStateFlow<ViewState<HomeViewState>> = MutableStateFlow(ViewState.loading())
    val homeViewState: StateFlow<ViewState<HomeViewState>> = _homeViewState

    override fun obtainEvent(event: HomeScreenEvent) {
        when (val result = _homeViewState.value) {
            is ViewState.Loading -> reduce(result, event)
            is ViewState.Successes -> reduce(result, event)
            is ViewState.Error -> reduce(result, event)
            is ViewState.Empty -> reduce(result, event)
        }
    }

    private fun reduce(viewState: ViewState.Error, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            else -> invalidEvent(event, viewState)
        }
    }

    private fun reduce(viewState: ViewState.Empty, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            is HomeScreenEvent.ChangeListArrangement -> updateListArrangement(event.listArrangement)
            else -> invalidEvent(event, viewState)
        }
    }

    private fun reduce(viewState: ViewState.Loading<HomeViewState>, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            else -> invalidEvent(event, viewState)
        }
    }

    private fun reduce(viewState: ViewState.Successes<HomeViewState>, event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            is HomeScreenEvent.SearchNotes -> searchNotes(event.query, viewState.data.listArrangement)
            is HomeScreenEvent.ChangeListArrangement -> updateListArrangement(event.listArrangement)
        }
    }

    private fun searchNotes(query: String, listArrangement: ListArrangement) {
        viewModelScope.launch {
            getNotes { list ->
                val resultList = list.filter { it.text.contains(query) || it.title.contains(query) }
                _homeViewState.value = ViewState.successes(HomeViewState(resultList, listArrangement))
            }
        }
    }

    private fun fetchNoteList() {
        viewModelScope.launch {
            _homeViewState.value = ViewState.loading()
            val listArrangement = appStorage.getListArrangement()
            getNotes { list ->
                if (list.isEmpty()) {
                    _homeViewState.value = ViewState.successes(HomeViewState(emptyList(), listArrangement))
                } else {
                    _homeViewState.value = ViewState.successes(HomeViewState(list, listArrangement))
                }
            }
        }
    }

    private suspend fun getNotes(result: (List<Note>) -> Unit) {
        noteRepository.getNoteList().getResult(
            successes = { list -> result(list ?: emptyList()) },
            error = { error -> ViewState.error(error) }
        )
    }

    private fun updateListArrangement(listArrangement: ListArrangement) {
        viewModelScope.launch {
            appStorage.updateListArrangement(listArrangement)
        }
    }

}