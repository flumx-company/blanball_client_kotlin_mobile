package com.example.blanball.presentation.views.components.lines

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp

@Composable
fun DottedLineUnderText(
    color: Color,
    textContent: @Composable () -> Unit,
) {
    var canvasWidth by remember { mutableStateOf(0f) }

    Box(modifier = Modifier.fillMaxWidth()) {
        var textContentWidth by remember { mutableStateOf(0) }

        // Измеряем ширину textContent
        Box(
            modifier = Modifier.fillMaxWidth() then Modifier.onGloballyPositioned { coordinates ->
                textContentWidth = coordinates.size.width
            }
        ) {
            textContent()
        }

        Spacer(modifier = Modifier.size(1.dp))

        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

        // Устанавливаем ширину Canvas равной ширине textContent
        Canvas(
            modifier = Modifier
                .width(textContentWidth.dp)
                .height(1.dp)
        ) {
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                pathEffect = pathEffect
            )
        }
    }
}