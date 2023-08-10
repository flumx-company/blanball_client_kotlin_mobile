package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CodeTextInput(
    state: StartScreensMainContract.State,
    modifier: Modifier,
    errorMessage: String = "",
    isError: Boolean? = null,
    enabled: Boolean = true,
) {
    val centerAlignedTextStyle = TextStyle(
        textAlign = TextAlign.Center
    )
    val localFocusManager = LocalFocusManager.current
    val focusRequesters = remember {
        List(5) { FocusRequester() }
    }
    Row {
        repeat(5) { i ->
            OutlinedTextField(
                isError = isError ?: false,
                textStyle = centerAlignedTextStyle,

                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .onKeyEvent {
                        if (it.key == Key.Backspace && i > 0) {
                            focusRequesters[i - 1].requestFocus()
                        }
                        true
                    }
                    .focusRequester(focusRequesters[i])
                    .padding(
                        end = when (i) {
                            in 0..3 -> 6.dp
                            else -> 0.dp
                        }
                    ),
                value = state.codeText[i].value,
                onValueChange = { newValue ->
                    state.codeText[i].value = newValue.take(1).uppercase()
                    when {
                        newValue.length == 1 && i < 4 -> focusRequesters[i + 1].requestFocus()
                    }
                },
                label = {Text("")},
                enabled = enabled,
                visualTransformation = VisualTransformation.None,
                shape = shapes.small,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = primaryDark,
                    errorBorderColor = errorRed,
                    cursorColor = mainGreen,
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = when (i) {
                        4 -> ImeAction.Done
                        else -> ImeAction.Next
                    }
                ),
                keyboardActions = when (i) {
                    4 -> KeyboardActions(onDone = { localFocusManager.clearFocus()})
                    else -> KeyboardActions.Default
                },
            )
        }
    }
    if (isError == true) {
        Text(text = errorMessage, style = typography.h6, color = errorRed)
    }
}