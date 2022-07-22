package com.app.noteapp.view.screen.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.noteapp.R
import com.app.noteapp.navigation.Router
import com.app.presentation.widget.BtnState
import com.app.presentation.widget.Loader
import com.app.presentation.widget.NoteAppButton
import com.app.presentation.widget.NoteAppTextField

@Composable
fun NoteScreen(
    router: Router,
    noteViewModel: NoteViewModel,
    noteScreenType: NoteScreenType
) {
    val noteViewState by remember { noteViewModel.noteScreenState }
    noteViewModel.handleNoteState(noteScreenType)

    when(noteViewState) {
        NoteScreenState.Loading -> { Loader() }
        NoteScreenState.Create -> {
            NoteScreenContent(
                title = "",
                text = "",
                btnText = noteViewState.btnText
            ) { title, text ->
                noteViewModel.createNote(title, text)
            }
        }
        is NoteScreenState.Edit -> {
            val note = (noteViewState as NoteScreenState.Edit).note
            NoteScreenContent(
                title = note.title,
                text = note.text,
                btnText = noteViewState.btnText
            ) { title, text ->
                noteViewModel.updateNote(title, text, note.id)
            }
        }
    }
}

@Composable
private fun NoteScreenContent(
    title: String,
    text: String,
    btnText: String,
    modifier: Modifier = Modifier,
    onBtnClick: (String, String) -> Unit,
) {

    var noteTitle by remember { mutableStateOf(title) }
    var noteText by remember { mutableStateOf(text) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            NoteAppTextField(
                value = noteTitle,
                onValueChange = { noteTitle = it },
                placeholderText = stringResource(id = R.string.title),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            NoteAppTextField(
                value = noteText,
                onValueChange = { noteText = it },
                placeholderText = stringResource(id = R.string.your_note_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 125.dp)
            )
            NoteAppButton(
                BtnState.Default(btnText) {
                    onBtnClick(noteTitle, noteText)
                }
            )
        }
    }
}

@Preview
@Composable
private fun NoteScreenContentPreview() {
    NoteScreenContent(
        title = "",
        text = "",
        btnText = "Create"
    ) { _, _ -> }
}