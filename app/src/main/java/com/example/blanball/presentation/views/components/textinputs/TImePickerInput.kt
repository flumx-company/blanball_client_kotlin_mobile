package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.accentLightGreen
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shadowDark
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTimePickerInAlertDialog(
    selectedTimeState: MutableState<String>,
    backBtnClicked: () -> Unit,
    isModalVisible: Boolean,
) {
    val timePickerState = rememberTimePickerState()
    val selectedTime = LocalTime.of(timePickerState.hour, timePickerState.minute)
    val formattedTime = selectedTime.format(DateTimeFormatter.ofPattern("HH:mm"))

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
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 24.dp)
                    .fillMaxWidth(0.8f)
                    .shadow(
                        elevation = 10.dp,
                        spotColor = shadowDark,
                        ambientColor = shadowDark
                    )
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
            ) {
                Column(
                    modifier =
                    Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.size(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.choose_the_event_time),
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(700),
                            color = primaryDark,
                        )
                    }
                    TimePicker(
                        modifier = Modifier.fillMaxWidth().scale(0.8f),
                        state = timePickerState,
                        colors = TimePickerDefaults.colors(
                            selectorColor = mainGreen,
                            clockDialColor = Color.White,
                            periodSelectorBorderColor = mainGreen,
                            periodSelectorSelectedContainerColor = accentLightGreen,
                            periodSelectorUnselectedContainerColor = Color.White,
                            periodSelectorSelectedContentColor = mainGreen,
                            periodSelectorUnselectedContentColor = mainGreen,
                            timeSelectorSelectedContainerColor = Color.White,
                            timeSelectorUnselectedContainerColor = Color.White,
                            timeSelectorSelectedContentColor = mainGreen,
                            timeSelectorUnselectedContentColor = mainGreen,
                        )
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            modifier = Modifier.clickable {
                                backBtnClicked()
                                selectedTimeState.value = ""
                            },
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = null,
                            tint = primaryDark
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            modifier = Modifier.clickable {
                                backBtnClicked()
                                selectedTimeState.value = formattedTime
                            },
                            painter = painterResource(id = R.drawable.ic_check),
                            contentDescription = null,
                            tint = primaryDark
                        )
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                }
            }
        }
    }
}