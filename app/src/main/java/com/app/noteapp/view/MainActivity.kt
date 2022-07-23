package com.app.noteapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.noteapp.navigation.Router
import com.app.noteapp.navigation.Screen
import com.app.noteapp.view.screen.home.HomeScreen
import com.app.noteapp.view.screen.note.NoteScreen
import com.app.presentation.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val router = Router(navController)
            NoteAppTheme {
                NavHost(navController = navController, startDestination = Screen.Home.route ) {
                    composable(Screen.Home.routeWithArgs) { HomeScreen(router = router, homeViewModel = hiltViewModel()) }
                    composable(
                        route = Screen.Note.routeWithArgs,
                        arguments = listOf(navArgument("noteId") {
                            nullable = true
                            defaultValue = null
                            type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getString("noteId")?.toLongOrNull()
                        NoteScreen(router = router, noteViewModel = hiltViewModel(), noteId)
                    }
                }
            }
        }
    }
}
