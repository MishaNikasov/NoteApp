package com.app.noteapp.view.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.domain.model.Note
import com.app.noteapp.R
import com.app.presentation.widget.Loader
import com.app.presentation.widget.NoteListItem
import com.app.util.State

@Composable
fun MainScreen(
    homeViewModel: HomeViewModel
) {
    val noteListState = homeViewModel.noteList.observeAsState()
    when (val result = noteListState.value) {
        is State.Loading -> { Loader() }
        is State.Successes -> { MainScreenContent(result.data) }
        else -> { }
    }
}

@Composable
private fun MainScreenContent(list: List<Note>) {
    Box(
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(list) { note ->
                    NoteListItem(note = note)
                }
            }
        }
    }
}