package com.app.noteapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.noteapp.view.screen.home.MainScreen
import com.app.noteapp.view.screen.note.NoteScreen
import com.app.noteapp.view.screen.note.NoteScreenType
import com.app.noteapp.view.theme.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                MainScreen(hiltViewModel())
            }
        }
    }
}
