package com.example.blanball.presentation.views.components.modals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun ExitFromAccModal(
    isModalVisible: Boolean,
    onCancelExitClicked: () -> Unit,
    onDeleteAccClicked: () -> Unit,
) {
    val configuration = LocalConfiguration.current

    AnimatedVisibility(
        visible = isModalVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 700)),
        exit = fadeOut(animationSpec = tween(durationMillis = 700))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .widthIn(max = configuration.screenWidthDp.dp - 20.dp)
                    .background(color = Color.White, shape = shapes.medium)
                    .padding(horizontal = 16.dp , vertical = 20.dp)
                    .wrapContentHeight()
            ) {
                Column {
                    Text(
                        text = stringResource(R.string.exit_from_account),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(600),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Text(
                        text = stringResource(R.string.you_want_exit_from_your_acc),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(600),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier
                            .clickable { onCancelExitClicked() }
                            .clip(shapes.medium)
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                        )
                        {
                            Text(
                                text = stringResource(R.string.cancel_exit),
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = primaryDark,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Box(modifier = Modifier
                            .clickable { onDeleteAccClicked() }
                            .clip(shapes.medium)
                            .padding(horizontal = 12.dp, vertical = 16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.exit_from_acc),
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = errorRed,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        }
    }
}