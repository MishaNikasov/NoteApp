package com.app.noteapp.view.screen.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.domain.model.Note
import com.app.noteapp.R
import com.app.presentation.theme.NoteAppTheme
import com.app.presentation.widget.NoteListItem
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContent(
    list: List<Note>,
    onItemSelect: (Note) -> Unit,
    onItemCreate: () -> Unit,
    onSearch: (String) -> Unit
) {

    var listArrangementState by remember { mutableStateOf(ListArrangement.List) }

    Scaffold(
        topBar = {
            HomeScreenTopBar(
                listArrangement = ListArrangement.List,
                onSearch = onSearch,
                onListArrangementSwap = { state -> listArrangementState = state },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = stringResource(id = R.string.your_notes),
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box {
                if (listArrangementState == ListArrangement.List) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        items(list) { note ->
                            NoteListItem(
                                note = note,
                                onClick = { onItemSelect(it) }
                            )
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding = PaddingValues(6.dp)
                    ) {
                        items(list) { note ->
                            NoteListItem(
                                note = note,
                                onClick = { onItemSelect(it) }
                            )
                        }
                    }
                }
                FloatingActionButton(
                    onClick = { onItemCreate() },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp)
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
            ), {}, {}, {}
        )
    }
}