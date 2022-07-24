package com.app.noteapp.view.screen.home

sealed class HomeScreenEvent {
    object FetchNotes: HomeScreenEvent()
}
