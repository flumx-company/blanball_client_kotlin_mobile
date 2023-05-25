package com.example.blanball.presentation.views.widgets.loaders

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
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
fun Loader(
    backgroundColor: Color = loadingBackgroundColor,
    textColor: Color = Color.White
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
        ) {
            RotatingImage()
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                modifier = Modifier
                    .align(CenterHorizontally),
                text = stringResource(id = R.string.loading),
                style = typography.h4,
                color = textColor,
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
                .size(133.3.dp)
                .graphicsLayer {}
        )
    }
}