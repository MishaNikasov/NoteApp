package com.app.presentation.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NoteAppButton(
    btnState: BtnState,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {

    Button(
        onClick = btnState.callback,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Green,
        ),
        enabled = btnState !is BtnState.Disabled,
        modifier = modifier
    ) {
        Text(
            text = btnState.text,
            color = Color.DarkGray
        )
    }

}

sealed class BtnState(open val text: String, open val callback: () -> Unit) {

    data class Default(override val text: String, override val callback: () -> Unit): BtnState(text, callback)
    data class Cancel(override val text: String, override val callback: () -> Unit): BtnState(text, callback)
    data class Disabled(override val text: String, override val callback: () -> Unit): BtnState(text, callback)

    val backgroundColor: Color
        get() {
            return when(this) {
                is Cancel -> Color.White
                is Default -> Color.White
                is Disabled -> Color.White
            }
        }

    val textColor: Color
        get() {
            return when(this) {
                is Cancel -> Color.White
                is Default -> Color.White
                is Disabled -> Color.White
            }
        }
}