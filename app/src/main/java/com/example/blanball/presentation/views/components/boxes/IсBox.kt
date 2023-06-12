package com.example.blanball.presentation.views.components.boxes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes

@Composable
fun IcBox(
    icon: Int,
    tint: Color = primaryDark,
    modifier: Modifier = Modifier.size(40.dp)
        .background(color = bgLight, shape = shapes.medium)
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center),
            tint = tint,
        )
    }
}