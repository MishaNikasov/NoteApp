package com.app.noteapp.view.screen.splash

import com.app.noteapp.navigation.Screen

sealed class AppState(val screen: Screen) {
    object Unauthorized: AppState(Screen.Auth)
    object Authorized: AppState(Screen.Home)
}
