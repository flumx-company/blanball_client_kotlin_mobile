package com.example.blanball.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(size = 0f),
    large = RoundedCornerShape(
        topStart = 28.dp,
        topEnd = 28.dp,
        bottomEnd = 0.dp,
        bottomStart = 0.dp
    )


)
