package com.example.blanball.presentation.views.components.modifiers

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.dottedLine(
    strokeWidth: Dp,
    color: Color,
    dashWidth: Dp = 2.dp,
    gapWidth: Dp = 2.dp
) = composed {
    val density = LocalDensity.current
    val strokeWidthPx = with(density) { strokeWidth.toPx() }
    val dashWidthPx = with(density) { dashWidth.toPx() }
    val gapWidthPx = with(density) { gapWidth.toPx() }
    drawBehind {
        val width = size.width
        val height = size.height

        drawLine(
            color = color,
            start = Offset(x = strokeWidthPx / 2, y = height - strokeWidthPx / 2),
            end = Offset(x = width - strokeWidthPx / 2, y = height - strokeWidthPx / 2),
            strokeWidth = strokeWidthPx,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidthPx, gapWidthPx), 0f)
        )
    }
}