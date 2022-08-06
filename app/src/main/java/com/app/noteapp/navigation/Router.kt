package com.app.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.noteapp.view.screen.auth.AuthScreen
import com.app.noteapp.view.screen.home.HomeScreen
import com.app.noteapp.view.screen.note.NoteScreen

class Router(
    private val navController: NavHostController,
    private val startDestination: Screen
) {

    @Composable
    fun NoteAppNavHost() {
        NavHost(navController = navController, startDestination = startDestination.route ) {
            composable(Screen.Auth.routeWithArgs) { AuthScreen(router = this@Router, authViewModel = hiltViewModel()) }
            composable(Screen.Home.routeWithArgs) { HomeScreen(router = this@Router, viewModel = hiltViewModel()) }
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

    fun navigateBack() = navController.navigateUp()

    fun clearBackStack() = navController.popBackStack()

    fun openNote(noteId: Long? = null) = navController.navigate(Screen.Note.putArgs(noteId.toString()))

    fun openHome() = navController.navigate(Screen.Home.route)

    fun openAuth() = navController.navigate(Screen.Auth.route)

}