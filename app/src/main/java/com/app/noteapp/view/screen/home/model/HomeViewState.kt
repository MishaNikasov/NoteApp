package com.app.noteapp.view.screen.home.model

import com.app.domain.model.ListArrangement
import com.app.domain.model.Note

sealed class HomeViewState {
    object Loading: HomeViewState()
    data class EmptyContent(val listArrangement: ListArrangement): HomeViewState()
    data class NoteListContent(val content: List<Note>, val listArrangement: ListArrangement): HomeViewState()
}