package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun BottomLineDefaultTextInput(
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
    Box(modifier = modifier,) {
        Column() {
        TextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = transformation,
            singleLine = true,
            textStyle =  TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.inter),
                ),
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                lineHeight = 24.sp,
            ),
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
}