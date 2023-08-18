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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
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
        textFieldModifier: Modifier = Modifier.fillMaxWidth(),
        labelResId: Int,
        state: UiState,
        value: String,
        isError: Boolean = false,
        onValueChange: (String) -> Unit,
        transformation: VisualTransformation,
        keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions: KeyboardActions = KeyboardActions.Default,
        errorMessage: String = "",
        trailingIcon: @Composable (() -> Unit)? = null,
        isSingleLine: Boolean = true,
    ) {
        Column(modifier = modifier) {
            OutlinedTextField(
                modifier = textFieldModifier,
                value = value,
                onValueChange = onValueChange,
                visualTransformation = transformation,
                singleLine = isSingleLine,
                label = {
                    Text(
                        stringResource(
                            id = labelResId
                        ),
                        color = primaryDark,
                        style = typography.h6,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 16.sp,
                    )
                },
                shape = shapes.small,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = primaryDark,
                    errorBorderColor = errorRed,
                    focusedLabelColor = primaryDark,
                    cursorColor = mainGreen,
                ),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                isError = isError,
                trailingIcon = trailingIcon,
            )
            if (isError) {
                Text(text = errorMessage, style = typography.h6, color = errorRed)
            }
        }
    }