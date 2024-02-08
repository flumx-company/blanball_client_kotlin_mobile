package com.example.blanball.presentation.views.components.icons

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark

@Composable
fun ExpandableIcon(
    isExpanded: Boolean,
    modifier: Modifier = Modifier,
    size: Dp = 11.25.dp,
    tint: Color = primaryDark
) {
    val painterResource = if (isExpanded) {
        painterResource(id = R.drawable.ic_arrow_up)
    } else {
        painterResource(id = R.drawable.ic_arrow_down)
    }

    Icon(
        painter = painterResource,
        modifier = modifier,
        tint = tint,
        contentDescription = null,
    )
}