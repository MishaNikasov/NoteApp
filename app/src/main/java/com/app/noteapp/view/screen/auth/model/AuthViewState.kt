package com.app.noteapp.view.screen.auth.model

sealed class AuthViewState {
    object Default: AuthViewState()
    object Loading: AuthViewState()
    object Error: AuthViewState()
    object AuthSuccesses: AuthViewState()
}