package com.app.noteapp.view.screen.home.model

import com.app.domain.model.ListArrangement
import com.app.util.ScreenEvent

sealed class HomeScreenEvent: ScreenEvent {
    object FetchNotes: HomeScreenEvent()
    data class SearchNotes(val query: String): HomeScreenEvent()
    data class ChangeListArrangement(val listArrangement: ListArrangement): HomeScreenEvent()
}
