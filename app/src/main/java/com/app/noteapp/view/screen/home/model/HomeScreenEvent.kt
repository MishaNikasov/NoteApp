package com.app.noteapp.view.screen.home.model

import com.app.domain.model.ListArrangement

sealed class HomeScreenEvent {
    object FetchNotes: HomeScreenEvent()
    data class SearchNotes(val query: String): HomeScreenEvent()
    data class ChangeListArrangement(val listArrangement: ListArrangement): HomeScreenEvent()
}
