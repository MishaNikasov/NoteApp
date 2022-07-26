package com.app.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Scampi,
    primaryVariant = Scampi,
    secondary = CreamBrulee
)

private val LightColorPalette = lightColors(
    primary = Scampi,
    onBackground = Scampi,
    primaryVariant = Scampi,
    secondary = LightPink ,
    background = Color.White,
    onPrimary = Color.White,
    onSecondary = Gainsboro
)

@Composable
fun NoteAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}