package com.example.blanball.presentation.views.widgets.loaders

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R


@Composable
fun Loader(
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
         RotatingImage()
    }
}

@Composable
fun RotatingImage() {
    val rotationAnim = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier.graphicsLayer(
            rotationZ = rotationAnim.value
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.steps_1),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                }
        )
    }
}