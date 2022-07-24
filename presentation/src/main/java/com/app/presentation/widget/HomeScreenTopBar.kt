package com.app.presentation.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.presentation.R
import com.app.presentation.theme.NoteAppTheme

enum class ListArrangement {
    List, Grid
}

@Composable
fun HomeScreenTopBar(
    listArrangement: ListArrangement,
    onSearch: (String) -> Unit,
    onListArrangementSwap: (ListArrangement) -> Unit,
    modifier: Modifier = Modifier
) {

    var listArrangementState by remember { mutableStateOf(listArrangement) }
    var editFieldVisibilityState by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                id =
                if (listArrangementState == ListArrangement.Grid)
                    R.drawable.ic_view_list
                else
                    R.drawable.ic_view_grid
            ),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(24.dp)
                .align(Alignment.CenterVertically)
                .padding(end = 12.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {
                    listArrangementState =
                        if (listArrangementState == ListArrangement.List)
                            ListArrangement.Grid
                        else
                            ListArrangement.List
                    onListArrangementSwap(listArrangementState)
                }
        )
        Image(
            painter = painterResource(
                id =
                if (editFieldVisibilityState)
                    R.drawable.ic_close
                else
                    R.drawable.ic_search
            ),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(24.dp)
                .align(Alignment.CenterVertically)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false)
                ) {
                    editFieldVisibilityState = !editFieldVisibilityState
                }
        )
        AnimatedVisibility(visible = editFieldVisibilityState) {
            NoteAppTextField(
                value = "",
                onValueChange = { onSearch(it) },
                placeholderText = "Search",
                requestFocus = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 4.dp, bottom = 4.dp)
            )
        }
    }
}
@Preview
@Composable
fun HomeScreenTopBarPreview() {
    NoteAppTheme {
        HomeScreenTopBar(ListArrangement.List, {}, {})
    }
}