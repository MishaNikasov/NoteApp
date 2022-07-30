package com.app.noteapp.view.screen.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.domain.model.ListArrangement
import com.app.domain.model.Note
import com.app.noteapp.R
import com.app.presentation.widget.NoteListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListContent(
    list: List<Note>,
    onItemSelect: (Note) -> Unit,
    listArrangement: ListArrangement,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
        if (listArrangement == ListArrangement.List) {
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
    }
}