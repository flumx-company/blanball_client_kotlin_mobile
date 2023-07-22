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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
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
                color = primaryDark,
                style = typography.h6,
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
            )
        },
        shape = shapes.small,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = defaultLightGray,
            focusedBorderColor = selectedDarkGray,
            textColor = primaryDark,
            errorBorderColor = errorRed,
            focusedLabelColor = primaryDark,
            cursorColor = mainGreen,
        ),
        trailingIcon = {
            IconButton(onClick = { visibilityIconState.value = !visibilityIconState.value}) {
                Icon(painter = painterResource(id = if (visibilityIconState.value) R.drawable.ic_eye_on else R.drawable.ic_eye_off), tint = secondaryNavy, contentDescription = "Visibility Icon")
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
