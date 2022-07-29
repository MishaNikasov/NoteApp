package com.app.noteapp.view.screen.home

import com.app.domain.model.ListArrangement

sealed class HomeScreenEvent {
    object FetchNotes: HomeScreenEvent()
    data class ChangeListArrangement(val listArrangement: ListArrangement): HomeScreenEvent()

}
