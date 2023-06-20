package com.example.blanball.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = primaryDark,
    secondary = secondaryNavy,
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    secondary = secondaryNavy,
    surface = VeryDarkGray
)

@Composable
fun MyAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = shapes,
        content = content,
    )
}
