package com.app.noteapp.view.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.storage.AppStorage
import com.app.noteapp.common.FirebaseAuthManager
import com.app.noteapp.view.screen.auth.model.AuthScreenEvent
import com.app.util.EventHandler
import com.app.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val appStorage: AppStorage
) : ViewModel(), EventHandler<AuthScreenEvent> {

    private val _authViewState = MutableStateFlow<ViewState<Unit>>(ViewState.empty())
    val authViewState: StateFlow<ViewState<Unit>> = _authViewState

    override fun obtainEvent(event: AuthScreenEvent) {
        when (event) {
            is AuthScreenEvent.Auth -> saveUserToken(event.userToken)
        }
    }

    private fun saveUserToken(token: String) {
        viewModelScope.launch {
            appStorage.updateUserToken(token)
            FirebaseAuthManager.firebaseAuthWithGoogle(token)
            _authViewState.value = ViewState.successes(Unit)
        }
    }

}