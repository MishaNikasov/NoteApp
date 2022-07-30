package com.app.noteapp.view.screen.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.domain.model.ListArrangement
import com.app.domain.model.Note
import com.app.noteapp.R
import com.app.presentation.theme.NoteAppTheme
import java.util.*

@Composable
fun HomeScreenContent(
    list: List<Note>,
    onItemSelect: (Note) -> Unit,
    onItemCreate: () -> Unit,
    onSearch: (String) -> Unit,
    listArrangement: ListArrangement,
    onListArrangementChange: (ListArrangement) -> Unit
) {

    var listArrangementState by remember { mutableStateOf(listArrangement) }

    Scaffold(
        topBar = {
            HomeScreenTopBar(
                listArrangement = listArrangementState,
                onSearch = onSearch,
                onListArrangementChange = { state ->
                    listArrangementState = state
                    onListArrangementChange(state)
                },
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            if (list.isEmpty()) {
                NoteListEmpty(modifier = Modifier.align(Alignment.Center))
            } else {
                NoteListContent(
                    list = list,
                    onItemSelect = onItemSelect,
                    listArrangement = listArrangementState,
                    modifier = Modifier
                )
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
            ), {}, {}, {}, ListArrangement.List
        ) { }
    }
}

@Preview
@Composable
fun HomeScreenEmptyContentPreview() {
    NoteAppTheme {
        HomeScreenContent(
            listOf(), {}, {}, {}, ListArrangement.List
        ) { }
    }
}