package com.app.noteapp.view.screen.auth.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.app.presentation.widget.BtnState
import com.app.presentation.widget.NoteAppButton

@Composable
fun AuthScreenContent(
    onSignInClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NoteAppButton(
            modifier = Modifier.align(Alignment.Center),
            btnState = BtnState.Default("Sign In") {
                onSignInClick()
            })
    }
}