package com.app.noteapp.view.screen.auth

sealed class AuthViewState {
    object Default: AuthViewState()
    object Loading: AuthViewState()
    object Error: AuthViewState()
    data class AuthSuccesses(val userToken: String): AuthViewState()
}