package com.app.noteapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.splash.SplashViewModel
import com.app.noteapp.view.screen.splash.SplashScreenState
import com.app.presentation.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.splashScreenState.value !is SplashScreenState.Finished
        }
        setContent {
            val splashScreenState by remember { splashViewModel.splashScreenState }
            when(splashScreenState) {
                SplashScreenState.InProgress -> {}
                SplashScreenState.Failed -> {}
                is SplashScreenState.Finished -> {
                    val appState = (splashScreenState as SplashScreenState.Finished).appState
                    val navController = rememberNavController()
                    val router = Router(
                        navController = navController,
                        startDestination = appState.screen
                    )
                    NoteAppTheme {
                        router.NoteAppNavHost()
                    }
                }
            }
        }
    }
}
