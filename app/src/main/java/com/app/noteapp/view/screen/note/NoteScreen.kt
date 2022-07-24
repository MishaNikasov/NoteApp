package com.app.noteapp.view.screen.note

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.noteapp.R
import com.app.noteapp.navigation.Router
import com.app.presentation.theme.NoteAppTheme
import com.app.presentation.widget.Loader
import com.app.presentation.widget.NoteAppTextField
import com.app.presentation.widget.NoteEditTopBar
import com.app.util.extensions.byPattern
import java.util.*

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
                date = Date(),
                onCloseClick = { router.navigateBack() },
                onDoneClick = { title, text ->
                    noteViewModel.obtainEvent(
                        NoteScreenEvent.CreateNote(title, text)
                    )
                }
            )
        }
        is NoteScreenState.Edit -> {
            val note = (noteViewState as NoteScreenState.Edit).note
            NoteScreenContent(
                title = note.title,
                text = note.text,
                onCloseClick = { router.navigateBack() },
                date = note.createDate,
                onDoneClick = { title, text ->
                    noteViewModel.obtainEvent(NoteScreenEvent.UpdateNote(title, text, note))
                },
                onDeleteClick = {
                    noteViewModel.obtainEvent(NoteScreenEvent.DeleteNote(note))
                }
            )
        }
    }

    LaunchedEffect(true) {
        noteViewModel.obtainEvent(NoteScreenEvent.HandleNoteState(noteId))
    }
}

@Composable
private fun NoteScreenContent(
    title: String,
    text: String,
    date: Date,
    onCloseClick: () -> Unit,
    onDoneClick: (String, String) -> Unit,
    onDeleteClick: (() -> Unit)? = null,
) {

    var noteTitle by remember { mutableStateOf(title) }
    var noteText by remember { mutableStateOf(text) }

    Scaffold(
        topBar = {
            NoteEditTopBar(
                onCloseClick = onCloseClick,
                onDoneClick = { onDoneClick(noteTitle, noteText) },
                onDeleteClick = onDeleteClick,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Text(
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.primary,
                text = date.byPattern(),
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.heightIn(4.dp))
            NoteAppTextField(
                value = noteTitle,
                onValueChange = { noteTitle = it },
                placeholderText = stringResource(id = R.string.title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.heightIn(4.dp))
            NoteAppTextField(
                value = noteText,
                onValueChange = { noteText = it },
                placeholderText = stringResource(id = R.string.your_note_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 125.dp)
                    .padding(horizontal = 12.dp),
                fontSize = 20.sp
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
            date = Date(),
            onCloseClick = { },
            onDoneClick = { _, _ -> }
        )
    }
}