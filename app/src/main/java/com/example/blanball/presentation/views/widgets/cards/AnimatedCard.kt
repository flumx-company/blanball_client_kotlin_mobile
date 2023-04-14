package com.example.blanball.presentation.views.widgets.cards

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowInsetsCompat

@Composable
fun AnimatedPaddingCard(
    modifier: Modifier = Modifier,
    paddingWithKeyboard: Dp,
    paddingWithoutKeyboard: Dp,
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    val insets = remember(view) { WindowInsetsCompat.toWindowInsetsCompat(view.rootWindowInsets) }

    val isKeyboardVisible = remember(insets) {
        insets.isVisible(WindowInsetsCompat.Type.ime())
    }

    val padding by animateDpAsState(
        if (isKeyboardVisible) paddingWithKeyboard else paddingWithoutKeyboard,
        tween(durationMillis = 1000)
    )

    Card(
        modifier = Modifier.padding(top = padding),
        content = content
    )
}
