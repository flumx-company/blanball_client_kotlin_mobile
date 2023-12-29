package com.example.blanball.presentation.views.components.modals

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun LookProfileFromTheSideModal(
    onNavigateToDemoClicked: () -> Unit,
    onSimpleSaveClicked: () -> Unit,
    onCancelEditsClicked: () -> Unit,
    onCloseModalClicked: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    AlertDialog(
        modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 40.dp)
            .wrapContentHeight()
            .background(color = primaryDark, shape = shapes.medium),
        onDismissRequest = { -> },
        buttons = { -> },
        title = {
            Row {
                Text(
                    text = stringResource(R.string.look_from_side),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(600),
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.clickable {
                        onCloseModalClicked()
                    },
                    painter = painterResource(id = R.drawable.ic_cancel),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.want_to_see_how),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = Color.White,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(color = mainGreen, shape = shapes.medium)
                            .clickable {
                                onNavigateToDemoClicked()
                            }
                            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.yes_nav_to_demo),
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                    Spacer(modifier = Modifier.size(6.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(color = primaryDark, shape = shapes.medium)
                            .border(width = 1.dp, color = Color.White, shape = shapes.small)
                            .clickable {
                                onSimpleSaveClicked()
                            }
                            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_sipmly_save),
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier
                            .clickable { onCancelEditsClicked() },
                        text = stringResource(R.string.cancel_edits),
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                }
            }
        },
        shape = shapes.medium,
        backgroundColor = primaryDark,
    )
}