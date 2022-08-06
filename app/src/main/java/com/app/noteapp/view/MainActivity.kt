package com.app.noteapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.app.noteapp.common.FirebaseLinkHandler
import com.app.noteapp.navigation.Router
import com.app.noteapp.navigation.Screen
import com.app.noteapp.view.screen.splash.SplashViewModel
import com.app.presentation.theme.NoteAppTheme
import com.app.util.ViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseLinkHandler.handle(intent)
        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.splashScreenState.value !is ViewState.Successes
        }
        setContent {
            val splashScreenState by remember { splashViewModel.splashScreenState }
            when(splashScreenState) {
                is ViewState.Successes -> {
                    val appState = (splashScreenState as ViewState.Successes).data.appState
                    val navController = rememberNavController()
                    val router = Router(
                        navController = navController,
                        startDestination = Screen.Home
                    // appState.screen
                    )
                    NoteAppTheme {
                        router.NoteAppNavHost()
                    }
                }
                else -> {}
            }
        }
    }
}
