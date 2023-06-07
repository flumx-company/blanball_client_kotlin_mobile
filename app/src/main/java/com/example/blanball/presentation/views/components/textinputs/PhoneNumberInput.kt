package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    errorMessage: String = "",
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(
                stringResource(
                    id = R.string.you_phone_number
                ),
            )
        },
        shape = shapes.small,
        leadingIcon = {
            Text(
                text = "+380",//TODO Make some resource
                modifier = Modifier.padding(start = 14.dp),
                color = Color.Black
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = defaultLightGray,
            focusedBorderColor = selectedDarkGray,
            textColor = Color.Black,
            errorBorderColor = errorRed,
            focusedLabelColor = primaryDark,
            cursorColor = mainGreen,
        ),
        keyboardOptions = keyboardOptions,
        isError = isError,
    )
    Column(modifier = modifier) {
        if (isError) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}