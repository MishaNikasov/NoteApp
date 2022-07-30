package com.app.noteapp.view.screen.auth.model

sealed class AuthScreenEvent {
    data class Auth(val userToken: String): AuthScreenEvent()
}