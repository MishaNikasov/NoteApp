package com.app.noteapp.view.screen.auth

sealed class AuthScreenEvent {
    data class Auth(val userToken: String): AuthScreenEvent()
}