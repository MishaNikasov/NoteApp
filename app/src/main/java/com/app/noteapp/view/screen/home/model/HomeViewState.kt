package com.app.noteapp.view.screen.home.model

import com.app.domain.model.ListArrangement
import com.app.domain.model.Note

data class HomeViewState(
    val content: List<Note>,
    val listArrangement: ListArrangement
)