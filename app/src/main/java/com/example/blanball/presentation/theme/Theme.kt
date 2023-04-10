package com.example.blanball.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = pink500,
    secondary = pink500,
    primaryVariant = pink600,
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

private val DarkColorPalette = darkColors(
    primary = pink200,
    secondary = pink200,
    surface = pinkDarkPrimary
)

@Composable
fun MyAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        LightColorPalette // DarkColorPallete - if we want DarkTheme
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
//        typography = typography,
        shapes = shapes,
        content = content,
    )
}
