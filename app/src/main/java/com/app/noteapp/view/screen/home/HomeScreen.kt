package com.app.noteapp.view.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.domain.model.Note
import com.app.noteapp.R
import com.app.noteapp.navigation.Router
import com.app.noteapp.view.screen.note.NoteScreenType
import com.app.presentation.theme.NoteAppTheme
import com.app.presentation.widget.Loader
import com.app.presentation.widget.NoteListItem
import com.app.presentation.widget.SearchBar
import com.app.util.State
import java.util.*

@Composable
fun HomeScreen(
    router: Router,
    homeViewModel: HomeViewModel
) {
    val noteListState = homeViewModel.noteList.observeAsState()
    when (val result = noteListState.value) {
        is State.Loading -> { Loader() }
        is State.Successes -> {
            HomeScreenContent(
                list = result.data,
                onItemSelect = { router.openNote(it.id) },
                onItemCreate = { router.openNote(null) }
            )
        }
        else -> { }
    }

    LaunchedEffect(true) {
        homeViewModel.fetchNoteList()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HomeScreenContent(
    list: List<Note>,
    onItemSelect: (Note) -> Unit,
    onItemCreate: () -> Unit
) {
    Scaffold(
        topBar = { SearchBar() },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.your_notes),
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(list) { note ->
                        NoteListItem(
                            note = note,
                            onClick = { onItemSelect(it) }
                        )
                    }
                }
                FloatingActionButton(
                    onClick = { onItemCreate() },
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenContentPreview() {
    NoteAppTheme {
        HomeScreenContent(
            listOf(
                Note(
                    id = 0,
                    title = "Title",
                    text = "Qwqe ewqeqw  qw eqw ew",
                    createDate = Date(),
                    editDate = Date()
                ),
                Note(
                    id = 0,
                    title = "Title",
                    text = "Qwqe 124214 124 21412 ewq 21412 ewq 21412 ewq 21412 ewq 21412 ewq",
                    createDate = Date(),
                    editDate = Date()
                ),
                Note(
                    id = 0,
                    title = "Title",
                    text = "Qwqe ewq",
                    createDate = Date(),
                    editDate = Date()
                ),
                Note(
                    id = 0,
                    title = "Title",
                    text = "Qwqe ewq",
                    createDate = Date(),
                    editDate = Date()
                ),
                Note(
                    id = 0,
                    title = "Title",
                    text = "Qwqe ew12312 31221  5125 123124 12515221 321321 512 512 312 12512q",
                    createDate = Date(),
                    editDate = Date()
                )
            ), { }, { }
        )
    }
}