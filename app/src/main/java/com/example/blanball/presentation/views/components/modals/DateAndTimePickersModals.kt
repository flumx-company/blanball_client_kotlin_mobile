package com.example.blanball.presentation.views.components.modals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.blanball.presentation.data.FutureEventsMainContract
import com.example.blanball.presentation.data.MyEventsScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate

//TODO("Need change colors inside this dialogs")

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerModal(
    selectedState: MutableState<String>,
    backBtnClicked: () -> Unit,
    isModalVisible: Boolean,
) {
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
            CalendarDialog(
                state = rememberUseCaseState(
                    visible = true,
                    embedded = true,
                    onCloseRequest = { backBtnClicked() }),
                config = CalendarConfig(
                    yearSelection = true,
                    style = CalendarStyle.MONTH,
                    boundary = LocalDate.now()..LocalDate.MAX,
                ),
                selection = CalendarSelection.Date { newDate ->
                    selectedState.value = newDate.toString()
                },
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DateRangePickerModal(
    backBtnClicked: () -> Unit,
    state: UiState,
    isModalVisible: Boolean,
) {
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
            CalendarDialog(
                state = rememberUseCaseState(visible = true, onCloseRequest = { backBtnClicked() }),
                config = CalendarConfig(
                    style = CalendarStyle.MONTH,
                    boundary = LocalDate.now()..LocalDate.MAX,
                ),
                selection = CalendarSelection.Period { startDate, endDate ->
                    when (state) {
                        is FutureEventsMainContract.State -> {
                            state.filterDateAndTimeAfter.value = startDate.toString()
                            state.filterDateAndTimeBefore.value = endDate.toString()
                        }

                        is MyEventsScreenMainContract.State -> {
                            state.filterDateAndTimeAfter.value = startDate.toString()
                            state.filterDateAndTimeBefore.value = endDate.toString()
                        }
                    }
                },
            )
        }
    }
}