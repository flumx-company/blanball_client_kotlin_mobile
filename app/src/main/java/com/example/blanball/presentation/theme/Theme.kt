package com.example.blanball.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = primaryDark,
    secondary = secondaryNavy,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    primaryVariant = mainGreen,
    secondaryVariant = mainGreen,

    )

private val DarkColorPalette = darkColors(
    primary = primaryDark,
    secondary = secondaryNavy,
    surface = VeryDarkGray,
    primaryVariant = mainGreen,
)


internal object MainGreenRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = mainGreenRippleEffect
    
    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleTheme.defaultRippleAlpha(
        Color.Unspecified,
        lightTheme = !isSystemInDarkTheme()
    )
}

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
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides MainGreenRippleTheme,
            content = content
        )
    }
}
