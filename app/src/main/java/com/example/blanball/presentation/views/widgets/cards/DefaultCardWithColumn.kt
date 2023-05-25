package com.example.blanball.presentation.views.widgets.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultCardWithColumn(content: @Composable ColumnScope.() -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
    ) {
        Column(
            Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 20.dp
            ), content = content,
        )
    }
}