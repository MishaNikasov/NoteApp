package com.app.noteapp.view.screen.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.data.storage.AppStorage
import com.app.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appStorage: AppStorage
): ViewModel() {

    var splashScreenState: MutableState<ViewState<SplashScreenState>> = mutableStateOf(ViewState.loading())

    init { checkAppState() }

    private fun checkAppState() {
        viewModelScope.launch {
            val appState = if (appStorage.getUserToken() == null) AppState.Unauthorized else AppState.Authorized
            splashScreenState.value = ViewState.successes(SplashScreenState(appState))
        }
    }

}