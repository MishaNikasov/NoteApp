package com.app.noteapp.view.screen.home.view

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NoteListEmpty(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "List is empty",
        style = MaterialTheme.typography.h2
    )
}