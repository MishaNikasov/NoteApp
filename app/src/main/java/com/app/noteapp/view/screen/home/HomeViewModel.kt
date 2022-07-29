package com.app.noteapp.view.screen.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.storage.AppStorage
import com.app.domain.model.ListArrangement
import com.app.domain.repository.NoteRepository
import com.app.util.EventHandler
import com.app.util.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val appStorage: AppStorage
) : ViewModel(), EventHandler<HomeScreenEvent> {

    var homeViewState: MutableState<HomeViewState> = mutableStateOf(HomeViewState.Loading)

    override fun obtainEvent(event: HomeScreenEvent) {
        when (event) {
            HomeScreenEvent.FetchNotes -> fetchNoteList()
            is HomeScreenEvent.ChangeListArrangement -> updateListArrangement(event.listArrangement)
        }
    }

    private fun fetchNoteList() {
        viewModelScope.launch {
            homeViewState.value = HomeViewState.Loading
            noteRepository.getNoteList().getResult(
                successes = { list ->
                    if (list.isNullOrEmpty()) {
                        homeViewState.value = HomeViewState.EmptyContent
                    } else {
                        homeViewState.value = HomeViewState.ShowContent(list, appStorage.getListArrangement())
                    }
                },
                error = { error ->
                    State.error(error)
                }
            )
        }
    }

    private fun updateListArrangement(listArrangement: ListArrangement) {
        viewModelScope.launch {
            appStorage.updateListArrangement(listArrangement)
        }
    }

}