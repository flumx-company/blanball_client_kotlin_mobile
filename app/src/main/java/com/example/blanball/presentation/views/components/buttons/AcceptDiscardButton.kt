package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.views.components.modifiers.BorderPosition
import com.example.blanball.presentation.views.components.modifiers.customBorder

@Composable
fun AcceptDiscardButtons(
    modifier: Modifier = Modifier,
    onAcceptRequest: () -> Unit,
    onDiscardRequest: () -> Unit,
) {
    Box(
        modifier = modifier
            .width(110.dp)
            .height(24.dp)
            .customBorder(
                strokeWidth = 1.dp,
                color = backgroundItems,
                position = BorderPosition.Start
            )
    ) {
        Row {
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(24.dp)
                    .padding(vertical = 16.dp, horizontal = 7.5.dp)
                    .border(
                        width = 1.dp,
                        color = backgroundItems,
                        shape = shapes.medium,
                    )
                    .clickable { onDiscardRequest() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_cancel),
                    tint = primaryDark,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .width(42.dp)
                    .height(24.dp)
                    .padding(vertical = 16.dp, horizontal = 7.5.dp)
                    .background(
                        color = mainGreen,
                        shape = shapes.medium,
                    )
                    .clickable { onAcceptRequest() },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_check),
                    tint = Color.White,
                    contentDescription = null,
                )
            }
        }
    }
}