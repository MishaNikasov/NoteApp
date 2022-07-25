package com.app.noteapp.view.screen.note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.note.view.NoteScreenContent
import com.app.presentation.widget.Loader
import java.util.*

@Composable
fun NoteScreen(
    router: Router,
    noteViewModel: NoteViewModel,
    noteId: Long?
) {
    val noteViewState by remember { noteViewModel.noteViewState }

    when (noteViewState) {
        NoteViewState.Loading -> Loader()
        NoteViewState.NoteUpdated -> router.navigateBack()
        NoteViewState.Create -> {
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
        is NoteViewState.Edit -> {
            val note = (noteViewState as NoteViewState.Edit).note
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