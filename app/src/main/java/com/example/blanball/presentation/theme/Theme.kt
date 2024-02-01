package com.example.blanball.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColorScheme(
    primary = mainGreen,
    secondary = primaryDark,
    onPrimary = Color.White,
    onSecondary = primaryDark,
    secondaryContainer = accentLightGreen,
    onSecondaryContainer = primaryDark,
    surface = Color.White,
    onSurface = primaryDark,
    inverseSurface = mainGreen,
    inverseOnSurface = mainGreen,
    error = errorRed,
    onError = errorRed,
    outline = mainGreen,
    outlineVariant = mainGreen,
    )

private val DarkColorPalette = darkColorScheme(
    primary = primaryDark,
    secondary = secondaryNavy,
    surface = VeryDarkGray,
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
      colorScheme = colors,
        shapes = shapes,
    ) {
        CompositionLocalProvider(
            LocalRippleTheme provides MainGreenRippleTheme,
            content = content
        )
    }
}
