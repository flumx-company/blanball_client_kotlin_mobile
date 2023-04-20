package com.example.blanball.presentation.views.widgets.cards

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.theme.shapes

@Composable
fun AnimatedPaddingCard(
    content: @Composable () -> Unit,
) {

    val isWindowFocused = LocalWindowInfo.current.isWindowFocused

    val padding by animateDpAsState(
        if (isWindowFocused) 0.dp
        else 120.dp,
        tween(durationMillis = 1000)
    )
    Card(
        modifier = Modifier.padding(top = padding).fillMaxSize(),
        content = content,
        shape = shapes.large,
    )
}
