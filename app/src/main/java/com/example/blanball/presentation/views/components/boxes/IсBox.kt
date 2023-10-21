package com.example.blanball.presentation.views.components.boxes

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.blanball.presentation.theme.primaryDark

@Composable
fun IcBox(
    modifier: Modifier = Modifier,
    icon: Int,
    tint: Color = primaryDark
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