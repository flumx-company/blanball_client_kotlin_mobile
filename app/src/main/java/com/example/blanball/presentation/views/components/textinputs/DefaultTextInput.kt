    package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

    @Composable
    fun DefaultTextInput(
        modifier: Modifier = Modifier,
        labelResId: Int,
        state: UiState,
        value: String,
        isError: Boolean = false,
        onValueChange: (String) -> Unit,
        transformation: VisualTransformation,
        keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        errorMessage: String = "",
    ) {
        Column(modifier = modifier) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChange,
                visualTransformation = transformation,
                singleLine = true,
                label = {
                    Text(
                        stringResource(
                            id = labelResId
                        ),
                    )
                },
                shape = shapes.small,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = Color.Black,
                    errorBorderColor = errorRed,
                    focusedLabelColor = primaryDark,
                    cursorColor = mainGreen,
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError,
        )
            if (isError) {
                Text(text = errorMessage, style = typography.h6, color = errorRed)
            }
        }
    }