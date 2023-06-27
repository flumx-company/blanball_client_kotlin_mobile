package com.example.blanball.presentation.views.components.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import kotlinx.coroutines.delay

@Composable
fun AnimationRotatingBalls() {

    val rotationAnim = remember {
        Animatable(initialValue = 0f,)
    }

    LaunchedEffect(Unit) {
        rotationAnim.animateTo(
            targetValue = 90f,
            animationSpec = repeatable(
                iterations = 1,
                animation = tween(durationMillis = 2000),
                repeatMode = RepeatMode.Restart
            ),
        )
        delay(2000)
    }

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Image(
            painter = painterResource(id = R.drawable.ball_1),
            contentDescription = null,
            modifier = Modifier
                .size(68.dp)
                .rotate(rotationAnim.value)
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.ball_1),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .offset(y = 40.dp)
                .rotate(rotationAnim.value)
        )
    }
}
