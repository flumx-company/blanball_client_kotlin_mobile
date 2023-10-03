package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography
import java.time.Instant
import java.time.ZoneId

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerModal(
    selectedState: MutableState<String?>,
    backBtnClicked: () -> Unit,
) {
    val configuration = LocalConfiguration.current
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    val selectedDateMillis = datePickerState.selectedDateMillis
    val formatedDateInstant = selectedDateMillis?.let { Instant.ofEpochMilli(it) }
    val localDate = formatedDateInstant?.atZone(ZoneId.systemDefault())?.toLocalDate()

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = {},
        buttons = {},
        text = {
            Column {
                Spacer(modifier = Modifier.size(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.chose_the_event_date),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                    )
                }
                    DatePicker(
                        showModeToggle = true,
                        modifier = Modifier.scale(0.8f),
                        state = datePickerState,
                        dateValidator = { timestamp ->
                            timestamp > Instant.now().toEpochMilli()
                        }
                    )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.clickable {
                            backBtnClicked()
                            selectedState.value = ""
                        },
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = primaryDark
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.clickable {
                            backBtnClicked()
                            selectedState.value = localDate.toString()
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