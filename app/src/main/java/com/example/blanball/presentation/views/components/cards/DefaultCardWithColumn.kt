package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCardWithColumn(
    padStart: Dp = 16.dp,
    padTop: Dp = 16.dp,
    padEnd: Dp = 16.dp,
    padBottom: Dp = 16.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 0.dp), shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            Modifier.padding(
                top = padTop,
                start = padStart,
                end = padEnd,
                bottom = padBottom
            ),
            content = content,
        )
    }
}