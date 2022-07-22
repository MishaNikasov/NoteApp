package com.app.noteapp.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home_screen")
    object Note: Screen("note_screen/{noteScreenState}")

}
