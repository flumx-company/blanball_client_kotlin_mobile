package com.example.blanball.presentation.views.widgets.textinputs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.theme.*


@Composable
fun CodeTextInput(
    state: MainContract.State,
    modifier: Modifier,
    enabled: Boolean = true,
) {
    Row () {
        repeat(5) { i ->
            OutlinedTextField(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(
                        end = when (i) {
                            in 0..3 -> 6.dp
                            else -> 0.dp
                        }
                    ),
                value = state.codeText[i].value,
                onValueChange = { newValue ->
                    state.codeText[i].value = newValue.take(1).uppercase()
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
            )
        }
    }
}