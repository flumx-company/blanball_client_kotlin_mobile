package com.example.blanball.presentation.views.widgets.textinputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.*

@Composable
fun CodeTextInput(
    state: StartScreensMainContract.State,
    modifier: Modifier,
    enabled: Boolean = true,
) {
    val centerAlignedTextStyle = TextStyle(
        textAlign = TextAlign.Center
    )
    val focusRequesters = remember {
        List(5) { FocusRequester() }
    }
    Row () {
        repeat(5) { i ->
            OutlinedTextField(
                textStyle = centerAlignedTextStyle,
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
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
                    if (newValue.length == 1 && i < 4) {
                        focusRequesters[i + 1].requestFocus()
                    }
                },
                label = { Text("") },
                enabled = enabled,
                visualTransformation = VisualTransformation.None,
                shape = shapes.small,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = Color.Black,
                    errorBorderColor = errorRed,
                    cursorColor = mainGreen,
                ),
                keyboardOptions = KeyboardOptions(imeAction = when (i) {
                    4 -> ImeAction.Done
                    else -> ImeAction.Next
                }),
            )
        }
    }
}