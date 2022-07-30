package com.app.noteapp.view.screen.auth

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.auth.view.AuthScreenContent
import com.app.noteapp.common.FirebaseAuthManager
import com.app.noteapp.view.screen.auth.model.AuthScreenEvent
import com.app.noteapp.view.screen.auth.model.AuthViewState
import com.google.android.gms.auth.api.signin.GoogleSignIn

@Composable
fun AuthScreen(
    router: Router,
    authViewModel: AuthViewModel
) {

    val authViewState = authViewModel.authViewState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            var userToken: String? = null
            runCatching {
                val account = task.result
                userToken = account.idToken ?: return@runCatching
            }.onSuccess {
                userToken?.let { token ->
                    authViewModel.obtainEvent(AuthScreenEvent.Auth(token))
                }
            }
        }
    )

    Log.d("AuthScreen", "AuthScreen: ${authViewState.value}")

    when(authViewState.value) {
        AuthViewState.Error -> {}
        AuthViewState.Loading -> {}
        AuthViewState.Default -> {
            val client = FirebaseAuthManager.getClient(LocalContext.current as Activity)
            AuthScreenContent(
                onSignInClick = {
                    launcher.launch(client.signInIntent)
                }
            )
        }
        is AuthViewState.AuthSuccesses -> {
            LaunchedEffect(true) {
                router.openHome()
            }
        }
    }

}
