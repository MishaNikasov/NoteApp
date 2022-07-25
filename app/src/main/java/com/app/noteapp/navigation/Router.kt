package com.app.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.noteapp.view.screen.home.HomeScreen
import com.app.noteapp.view.screen.note.NoteScreen

class Router(
    private val navController: NavHostController
) {

    @Composable
    fun NoteAppNavHost() {
        NavHost(navController = navController, startDestination = Screen.Home.route ) {
            composable(Screen.Home.routeWithArgs) { HomeScreen(router = this@Router, homeViewModel = hiltViewModel()) }
            composable(
                route = Screen.Note.routeWithArgs,
                arguments = listOf(navArgument("noteId") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType }
                )
            ) { backStackEntry ->
                val noteId = backStackEntry.arguments?.getString("noteId")?.toLongOrNull()
                NoteScreen(router = this@Router, noteViewModel = hiltViewModel(), noteId)
            }
        }
    }

    fun openNote(noteId: Long?) {
        navController.navigate(Screen.Note.putArgs(noteId.toString()))
    }

    fun navigateBack() {
        navController.navigateUp()
    }

}