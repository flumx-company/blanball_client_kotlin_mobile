package com.example.blanball.presentation.views.widgets.loaders

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.loadingBackgroundColor
import com.example.blanball.presentation.theme.typography


@Composable
fun Loader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(loadingBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
        ) {
            RotatingImage()
            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(CenterHorizontally),
                text = stringResource(id = R.string.loading),
                style = typography.h4,
                color = Color.White,
            )
        }
    }
}

@Composable
fun RotatingImage() {
    val rotationAnim = rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        )
    )

    Box(
        modifier = Modifier.graphicsLayer(
            rotationZ = rotationAnim.value,
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