package com.app.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.presentation.R
import com.app.presentation.theme.NoteAppTheme

@Composable
fun NoteEditTopBar(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_close),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {

                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_check),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(24.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {

                }
        )
    }
}

@Preview
@Composable
fun NoteEditTopBarPreview() {
    NoteAppTheme {
        NoteEditTopBar()
    }
}