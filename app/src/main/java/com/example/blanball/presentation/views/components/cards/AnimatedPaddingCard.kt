package com.example.blanball.presentation.views.components.cards

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedPaddingCard(
    content: @Composable () -> Unit,
) {
    val isWindowFocused = LocalWindowInfo.current.isWindowFocused

    val padding by animateDpAsState( targetValue =
        if (isWindowFocused) 0.dp
        else 200.dp,
        tween(durationMillis = 1000)
    )

    val shape by animateDpAsState(targetValue =
    if (isWindowFocused) 0.dp
    else 28.dp,
    tween(durationMillis = 1000)
    )

    Card(
        modifier = Modifier
            .padding(top = padding)
            .fillMaxSize(),
        content = content,
        shape = RoundedCornerShape(shape),
    )
}
