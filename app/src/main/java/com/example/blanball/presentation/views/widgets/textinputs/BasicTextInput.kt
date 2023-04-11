package com.example.blanball.presentation.views.widgets.textinputs

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.theme.*


@Composable
fun BasicTextInput(
    labelResId: Int,
    transformation: VisualTransformation,
    state: MainContract.State,
    modifier: Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.emailText.value,
        onValueChange = { state.emailText.value = it },
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
        )
    )
}