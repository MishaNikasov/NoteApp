package com.app.noteapp.view.screen.splash

sealed class SplashScreenState {
    object InProgress: SplashScreenState()
    object Failed: SplashScreenState()
    data class Finished(val appState: AppState): SplashScreenState()
}