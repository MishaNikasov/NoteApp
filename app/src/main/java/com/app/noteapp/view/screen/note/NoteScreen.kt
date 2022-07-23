package com.app.noteapp.view.screen.note

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.noteapp.R
import com.app.noteapp.navigation.Router
import com.app.presentation.theme.NoteAppTheme
import com.app.presentation.widget.*

@Composable
fun NoteScreen(
    router: Router,
    noteViewModel: NoteViewModel,
    noteId: Long?
) {
    val noteViewState by remember { noteViewModel.noteScreenState }

    when (noteViewState) {
        NoteScreenState.Loading -> Loader()
        NoteScreenState.NoteUpdated -> router.navigateBack()
        NoteScreenState.Create -> {
            NoteScreenContent(
                title = "",
                text = "",
                btnText = noteViewState.btnText,
                onBackArrowClick = { router.navigateBack() }
            ) { title, text ->
                noteViewModel.createNote(title, text)
            }
        }
        is NoteScreenState.Edit -> {
            val note = (noteViewState as NoteScreenState.Edit).note
            NoteScreenContent(
                title = note.title,
                text = note.text,
                btnText = noteViewState.btnText,
                onBackArrowClick = { router.navigateBack() }
            ) { title, text ->
                noteViewModel.updateNote(title, text, note)
            }
        }
    }

    LaunchedEffect(true) {
        noteViewModel.handleNoteState(noteId)
    }
}

@Composable
private fun NoteScreenContent(
    title: String,
    text: String,
    btnText: String,
    modifier: Modifier = Modifier,
    onBackArrowClick: () -> Unit,
    onBtnClick: (String, String) -> Unit,
) {

    var noteTitle by remember { mutableStateOf(title) }
    var noteText by remember { mutableStateOf(text) }

    Scaffold(
        topBar = {
            NoteEditTopBar()
        }
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
        }
    }
}

@Preview
@Composable
private fun NoteScreenContentPreview() {
    NoteAppTheme {
        NoteScreenContent(
            title = "",
            text = "",
            btnText = "Create",
            onBackArrowClick = { }
        ) { _, _ -> }
    }
}