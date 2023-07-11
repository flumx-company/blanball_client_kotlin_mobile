package com.example.blanball.presentation.views.components.textinputs
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun PassTextInput(
    labelResId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    state: StartScreensMainContract.State,
    isError: Boolean = false,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    errorMessage: String = "",
    visibilityIconState: MutableState<Boolean>
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (visibilityIconState.value) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        label = {
            Text(
                stringResource(
                    id = labelResId
                ),
            )
        },
        shape = shapes.small,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = defaultLightGray,
            focusedBorderColor = selectedDarkGray,
            textColor = Color.Black,
            errorBorderColor = errorRed,
            focusedLabelColor = primaryDark,
            cursorColor = mainGreen,
        ),
        trailingIcon = {
            IconButton(onClick = { visibilityIconState.value = !visibilityIconState.value}) {
                Icon(painter = painterResource(id = if (visibilityIconState.value) R.drawable.ic_eye_on else R.drawable.ic_eye_off), contentDescription = "Visibility Icon")
            }
        },
        isError = isError
    )
    Column(modifier = modifier) {
        if (isError) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}
