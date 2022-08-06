package com.app.noteapp.view.screen.note

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.note.model.NoteScreenEvent
import com.app.noteapp.view.screen.note.model.NoteViewState
import com.app.noteapp.view.screen.note.view.NoteScreenContent
import com.app.presentation.widget.Loader
import com.app.util.ViewState
import java.util.*

@Composable
fun NoteScreen(
    router: Router,
    noteViewModel: NoteViewModel,
    noteId: Long?
) {
    val noteViewState by noteViewModel.noteViewState.collectAsState()

    when (noteViewState) {
        is ViewState.Loading -> Loader()
        is ViewState.Successes -> {
            when (val data = (noteViewState as ViewState.Successes<NoteViewState>).data) {
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
                    val note = data.note
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
        }
        else -> {}
    }

    LaunchedEffect(true) {
        noteViewModel.obtainEvent(NoteScreenEvent.HandleNoteState(noteId))
    }
}