package com.app.presentation.widget

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.domain.model.Note
import com.app.presentation.theme.NoteAppTheme
import com.app.util.extensions.byPattern
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
        shape = RoundedCornerShape(32.dp),
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(120.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(24.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h1,
                text = note.title
            )
            Text(
                style = MaterialTheme.typography.caption,
                text = note.createDate.byPattern(),
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic
            )
            Text(
                style = MaterialTheme.typography.subtitle1,
                text = note.text
            )
        }
    }
}

@Preview
@Composable
fun NoteListItemPreview() {
    NoteAppTheme {
        NoteListItem(
            note = Note(
                id = 12,
                title = "Title",
                text = "Qwqe ewqeqw ewqe qwe wq eqw e qw eqw ew",
                createDate = Date()
            )
        )
    }
}