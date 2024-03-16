package com.example.blanball.presentation.views.components.dropdownmenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContextDropdownMenuButton(
    modifier: Modifier = Modifier,
    btnTextResId: Int,
    onJoinBtnAsPlayerClick: () -> Unit,
    onJoinBtnAsFunClick: () -> Unit,
) {
    val listOfContextBtnItems: List<Triple<String, Int, () -> Unit>> = listOf(
        Triple(stringResource(R.string.play), R.drawable.ic_ball) { onJoinBtnAsPlayerClick() },
        Triple(stringResource(R.string.cheer), R.drawable.ic_cheer) { onJoinBtnAsFunClick() }
    )
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column (
                Modifier.wrapContentWidth().animateContentSize(),
            ) {
                AnimatedVisibility(
                    visible = expanded,
                ) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { !expanded },
                        modifier = Modifier.wrapContentWidth(),
                    ) {
                        listOfContextBtnItems.forEach { selectedOption ->
                            DropdownMenuItem(
                                modifier = Modifier.wrapContentWidth(),
                                onClick = {
                                    selectedOption.third.invoke()
                                    expanded = !expanded
                                }) {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(selectedOption.second),
                                    tint = primaryDark,
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = selectedOption.first,
                                    style = typography.h6,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight(400),
                                    lineHeight = 20.sp,
                                    color = primaryDark
                                )
                            }
                        }
                    }
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(136.dp)
                        .height(40.dp),
                    shape = shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = mainGreen,
                        contentColor = Color.White,
                    ),
                ) {
                        Text(
                            modifier = Modifier,
                            text = stringResource(btnTextResId),
                            fontSize = 15.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                        )
                    }
                }
            }
        }
    }