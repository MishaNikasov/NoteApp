package com.app.noteapp.navigation

import androidx.navigation.NavController
import com.app.noteapp.view.screen.note.NoteScreenType

class Router(
    private val navController: NavController
) {

    fun openNote(noteScreenType: NoteScreenType) {
        navController.navigate(Screen.Note.route)
    }

}