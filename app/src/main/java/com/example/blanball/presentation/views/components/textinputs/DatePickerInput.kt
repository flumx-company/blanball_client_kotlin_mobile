package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import java.time.Instant

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SimpleDatePickerInDatePickerDialog(
    openDialog: Boolean,
    onDismiss: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    DatePickerDialog(

        modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 20.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(6.dp),
        onDismissRequest = onDismiss,
        confirmButton = {
            // Seems broken at the moment with DateRangePicker
            // Works fine with DatePicker
        },
        colors = DatePickerDefaults.colors(
            selectedDayContainerColor = mainGreen,
            todayDateBorderColor = mainGreen),
    ) {
        DatePicker(
            state = datePickerState,
            dateValidator = { timestamp ->
                timestamp > Instant.now().toEpochMilli()
            }
        )
    }
}