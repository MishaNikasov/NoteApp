package com.app.noteapp.view.screen.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.storage.AppStorage
import com.app.util.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val appStorage: AppStorage
): ViewModel(), EventHandler<AuthScreenEvent> {

    val authViewState = mutableStateOf<AuthViewState>(AuthViewState.Default)

    override fun obtainEvent(event: AuthScreenEvent) {
        when(event) {
            is AuthScreenEvent.Auth -> saveUserToken(event.userToken)
        }
    }

    private fun saveUserToken(token: String) {
        viewModelScope.launch {
            appStorage.updateUserToken(token)
            authViewState.value = AuthViewState.AuthSuccesses(token)
        }
    }

}