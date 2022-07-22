package com.app.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteAppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText : String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = placeholderText)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                backgroundColor = Color.White
            ),
            modifier = modifier
        )
    }
}