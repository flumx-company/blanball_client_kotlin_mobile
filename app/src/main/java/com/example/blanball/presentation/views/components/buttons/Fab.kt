package com.example.blanball.presentation.views.components.buttons

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.domain.utils.Integers

@Stable
@Composable
fun Fab(
    modifier: Modifier,
    clickCallback: () -> Unit,
) {
    val rotationState = remember { mutableStateOf(0f) }
    Box(modifier = modifier) {
        FloatingActionButton(
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            onClick = {
                clickCallback()
                rotationState.value += 360f
            },
            backgroundColor = mainGreen,
            modifier = Modifier.graphicsLayer(
                rotationX = animateFloatAsState(
                    rotationState.value,
                    tween(Integers.DURATION_MILLIS_ON_CARD),
                ).value,
            ),
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = Color.White
                )
            },
        )
    }
}