package com.app.noteapp.view.screen.home

import com.app.domain.model.Note

sealed class HomeViewState {

    object Loading: HomeViewState()
    object EmptyContent: HomeViewState()
    data class ShowContent(val content: List<Note>): HomeViewState()

}