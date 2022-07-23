package com.app.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.presentation.R

@Composable
fun NoteAppToolbar(
    title: String,
    onBackArrowClick: () -> Unit,
    content: @Composable RowScope.() -> Unit = { }
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {
                    onBackArrowClick()
                }
        )
        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp)
        )
        content()
    }
}