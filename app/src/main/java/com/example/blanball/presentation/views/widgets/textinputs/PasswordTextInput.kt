package com.example.blanball.presentation.views.widgets.textinputs
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.blanball.R
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.theme.*

@Composable
fun PassTextInput(
    labelResId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    state: MainContract.State,
    modifier: Modifier,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (state.passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
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
        trailingIcon = {
            IconButton(onClick = { state.passwordVisibility.value = !state.passwordVisibility.value}) {
                Icon(painter = painterResource(id = if (state.passwordVisibility.value) R.drawable.ic_eye_on else R.drawable.ic_eye_off), contentDescription = "Visibility Icon")
            }
        }
    )
}
