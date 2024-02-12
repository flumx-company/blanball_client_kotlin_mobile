package com.example.blanball.presentation.views.components.modifiers


import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

enum class BorderPosition {
    Start, End, Bottom, Left
}

fun Modifier.customBorder(
    strokeWidth: Dp,
    color: Color,
    position: BorderPosition
) = composed {
    val density = LocalDensity.current
    val strokeWidthPx = with(density) { strokeWidth.toPx() }

    drawBehind {
        val width = size.width
        val height = size.height

        when (position) {
            BorderPosition.Start -> {
                drawLine(
                    color = color,
                    start = Offset(x = strokeWidthPx / 2, y = 0f),
                    end = Offset(x = strokeWidthPx / 2, y = height),
                    strokeWidth = strokeWidthPx
                )
            }
            BorderPosition.End -> {
                drawLine(
                    color = color,
                    start = Offset(x = width - strokeWidthPx / 2, y = 0f),
                    end = Offset(x = width - strokeWidthPx / 2, y = height),
                    strokeWidth = strokeWidthPx
                )
            }
            BorderPosition.Bottom -> {
                drawLine(
                    color = color,
                    start = Offset(x = 0f, y = height - strokeWidthPx / 2),
                    end = Offset(x = width, y = height - strokeWidthPx / 2),
                    strokeWidth = strokeWidthPx
                )
            }
            BorderPosition.Left -> {
                drawLine(
                    color = color,
                    start = Offset(x = strokeWidthPx / 2, y = 0f),
                    end = Offset(x = strokeWidthPx / 2, y = height),
                    strokeWidth = strokeWidthPx
                )
            }
        }
    }
}