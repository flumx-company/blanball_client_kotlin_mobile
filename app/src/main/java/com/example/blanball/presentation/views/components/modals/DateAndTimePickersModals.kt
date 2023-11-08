package com.example.blanball.presentation.views.components.modals

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DateRangePickerModal(
    backBtnClicked: () -> Unit,
    state: UiState,
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