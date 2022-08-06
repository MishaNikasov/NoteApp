package com.app.noteapp.view.screen.auth.model

import com.app.util.ScreenEvent

sealed class AuthScreenEvent: ScreenEvent {
    data class Auth(val userToken: String): AuthScreenEvent()
}