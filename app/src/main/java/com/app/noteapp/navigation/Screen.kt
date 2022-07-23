package com.app.noteapp.navigation

sealed class Screen(val route: String, val routeWithArgs: String) {
    object Home: Screen("home_screen", "home_screen")
    object Note: Screen("note_screen", "note_screen?noteId={noteId}")

    fun putArgs(vararg args: Any?): String {
        return when(this) {
            Home -> ""
            Note -> "note_screen?noteId=${args.first()}"
        }
    }

}
