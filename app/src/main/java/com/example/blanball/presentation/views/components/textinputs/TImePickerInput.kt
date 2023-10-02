package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import java.time.Instant
import java.time.ZoneId

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleTimePickerInAlertDialog(
    selectedTimeState: MutableState<String?>,
    backBtnClicked: () -> Unit,
) {
    val timePickerState = rememberTimePickerState()
    val selectedTimeMillis = timePickerState.hour.toLong()

    val formattedTime = selectedTimeMillis?.let {
        val instant = Instant.ofEpochMilli(it)
        val time = instant.atZone(ZoneId.systemDefault()).toLocalTime()
        time.toString()
    }

    selectedTimeState.value = formattedTime

    AlertDialog(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = {},
        buttons = {},
        text = {
            Column {
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.choose_the_event_time),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(700),
                        color = primaryDark
                    )
                }
                TimePicker(
                    modifier = Modifier.scale(0.8f),
                    state = timePickerState
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.clickable {
                            backBtnClicked()
                        },
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = primaryDark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.clickable {
                            backBtnClicked()
                        },
                        painter = painterResource(id = R.drawable.ic_check),
                        contentDescription = null,
                        tint = primaryDark
                    )
                }
            }
        }
    )
}