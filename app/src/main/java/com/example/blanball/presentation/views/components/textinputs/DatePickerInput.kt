package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import java.time.LocalDate


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DatePickerModal(
    selectedState: MutableState<String>,
    backBtnClicked: () -> Unit,
) {
    CalendarDialog(
        state = rememberUseCaseState(visible = true, true, onCloseRequest = { backBtnClicked }),
        config = CalendarConfig(
            yearSelection = true,
            style = CalendarStyle.MONTH,
        ),
        selection = CalendarSelection.Date(
            selectedDate = LocalDate.parse(selectedState.value)
        ) { newDate ->
            selectedState.value = newDate.toString()
        },
    )
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DateRangePickerModal(
    backBtnClicked: () -> Unit,
    filterDateAndTimeAfter: MutableState<String>,
    filterDateAndTimeBefore: MutableState<String>,
) {
    CalendarDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { backBtnClicked() }),
        config = CalendarConfig(
            style = CalendarStyle.MONTH,
            boundary = LocalDate.now()..LocalDate.MAX,
        ),
        selection = CalendarSelection.Period(
        ) { startDate, endDate ->
            filterDateAndTimeAfter.value = startDate.toString()
            filterDateAndTimeBefore.value = endDate.toString()
        },
    )
}