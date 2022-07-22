package com.app.presentation.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.domain.model.Note
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NoteListItem(
    note: Note,
    modifier: Modifier = Modifier,
    onClick: (Note) -> Unit = { }
) {
    Card(
        onClick = { onClick(note) },
        shape = RoundedCornerShape(4.dp),
        elevation = 3.dp
    ) {
        Column(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = note.title,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
            Text(
                text = note.text,
                color = Color.DarkGray,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
fun NoteListItemPreview() {
//    NoteListItem(
//        note = Note(
//            id = "123",
//            title = "Title",
//            text = "Qwqe ewqeqw ewqe qwe wq eqw e qw eqw ew",
//            date = Date()
//        )
//    )
}