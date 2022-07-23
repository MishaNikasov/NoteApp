package com.app.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.presentation.theme.NoteAppTheme

@Composable
fun NoteAppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText : String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
        modifier = modifier
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholderText,
                    style = MaterialTheme.typography.subtitle1
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onSecondary,
                backgroundColor = MaterialTheme.colors.background
            )
        )
    }
}

@Preview
@Composable
fun NoteAppTextFieldPreview() {
    NoteAppTheme {
        NoteAppTextField(
            value = "",
            onValueChange = {},
            placeholderText = "Placeholder"
        )
    }
}