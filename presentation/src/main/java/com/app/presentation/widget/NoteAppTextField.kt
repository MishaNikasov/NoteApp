package com.app.presentation.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.app.presentation.theme.NoteAppTheme

@Composable
fun NoteAppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    textColor: Color = MaterialTheme.colors.primary,
    placeholderColor: Color = MaterialTheme.colors.onSecondary,
    requestFocus: Boolean = false
) {

    var text by remember { mutableStateOf(value) }
    val focusRequester = FocusRequester()

    Box(
        modifier = modifier
    ) {
        if (text.isEmpty()) {
            Text(
                text = placeholderText,
                style = TextStyle(
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    color = placeholderColor
                ),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        BasicTextField(
            value = text,
            onValueChange = {
                onValueChange(it)
                text = it
            },
            textStyle = TextStyle(
                fontSize = fontSize,
                fontWeight = fontWeight,
                color = textColor
            ),
            cursorBrush = SolidColor(MaterialTheme.colors.primary),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
    }

    LaunchedEffect(key1 = true) {
        if (requestFocus) {
            focusRequester.requestFocus()
        }
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