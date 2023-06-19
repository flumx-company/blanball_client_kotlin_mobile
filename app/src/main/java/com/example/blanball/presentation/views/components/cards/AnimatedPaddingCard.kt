package com.example.blanball.presentation.views.components.cards

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.domain.utils.Integers

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedPaddingCard(
    content: @Composable () -> Unit,
) {
    val targetValuePadding: MutableState<Dp> = remember {
        mutableStateOf(200.dp)
    }
    val targetValueShape: MutableState<Dp> = remember {
        mutableStateOf(28.dp)
    }

    val isImeVisible = WindowInsets.isImeVisible

    remember(isImeVisible) {
        if (isImeVisible) targetValuePadding.value = 20.dp else 200.dp
        if (isImeVisible) targetValueShape. value = 0.dp else 28.dp
    }

    val padding by animateDpAsState( targetValue =
         targetValuePadding.value,
        tween(durationMillis = Integers.DURATION_MILLIS_ON_CARD)
    )

    val shape by animateDpAsState(targetValue =
    targetValueShape.value,
    tween(durationMillis = Integers.DURATION_MILLIS_ON_CARD)
    )

    Card(
        modifier = Modifier
            .padding(top = padding)
            .fillMaxSize(),
        content = content,
        shape = RoundedCornerShape(shape),
    )
}
