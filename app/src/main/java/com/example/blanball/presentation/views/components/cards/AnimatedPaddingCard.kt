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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.domain.utils.Integers

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedPaddingCard(
    content: @Composable () -> Unit,
    enableAnimation: Boolean,
) {
    val keyboardVisible = remember { mutableStateOf(false) }

    val isKeyboardVisible = WindowInsets.isImeVisible

    LaunchedEffect(key1 = isKeyboardVisible) {
        keyboardVisible.value = isKeyboardVisible
    }

    val padding by animateDpAsState(
        targetValue = if (keyboardVisible.value && enableAnimation) 0.dp else 190.dp,
        tween(durationMillis = Integers.DURATION_MILLIS_ON_CARD)
    )

    val shape by animateDpAsState(
        targetValue = if (keyboardVisible.value && enableAnimation) 0.dp else 28.dp,
        tween(durationMillis = Integers.DURATION_MILLIS_ON_CARD)
    )

    Card(
        modifier = Modifier
            .padding(top = padding)
            .fillMaxSize().shadow(elevation = 12.dp, shape = RoundedCornerShape(topStart = shape, topEnd = shape, bottomStart = 0.dp, bottomEnd = 0.dp)),
        content = content,
        shape = RoundedCornerShape(topStart = shape, topEnd = shape, bottomStart = 0.dp, bottomEnd = 0.dp,),
    )
}