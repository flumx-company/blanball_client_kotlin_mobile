package com.example.blanball.presentation.views.components.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.surfaceBrandSecondary
import com.example.blanball.presentation.theme.typography

@Composable
fun SuccessMessageCard(
    text: String,
    isMessageVisible: Boolean,
    onCancelIconClicked: () -> Unit,
) {
    AnimatedVisibility(
        visible = isMessageVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300))
    ) {
        Column(
            modifier = Modifier.padding(
                10.dp
            ),
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = mainGreen, shape = shapes.medium)
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(color = surfaceBrandSecondary, shape = shapes.medium)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(21.5.dp),
                        painter = painterResource(R.drawable.ic_check_field),
                        contentDescription = null,
                        tint = errorRed,
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = text,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(10.dp))
                    Icon(
                        modifier = Modifier.clickable { onCancelIconClicked() },
                        painter = painterResource(R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = primaryDark,
                    )
                }
            }
        }
    }
}