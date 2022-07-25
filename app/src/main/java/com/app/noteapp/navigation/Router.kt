package com.app.noteapp.navigation

import androidx.navigation.NavController

class Router(
    private val navController: NavController
) {

    fun openNote(noteId: Long?) {
        navController.navigate(Screen.Note.putArgs(noteId.toString()))
    }

    fun navigateBack() {
        navController.navigateUp()
    }

}