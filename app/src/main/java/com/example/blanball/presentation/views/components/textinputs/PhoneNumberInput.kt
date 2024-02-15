package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.MaskVisualTransformation
import com.example.domain.utils.Formats

@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    errorMessage: String = "",
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            color = primaryDark,
            fontSize = 13.sp,
            fontWeight = FontWeight(400),
            fontFamily = FontFamily(Font(R.font.inter)),
            lineHeight = 24.sp,
            textMotion = TextMotion.Animated,
        ),
        label = {
            Text(
                stringResource(
                    id = R.string.you_phone_number
                ),
                color = primaryDark,
                style = typography.h6,
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
            )
        },
        shape = shapes.small,
        leadingIcon = {
            Text(
                text = stringResource(id = R.string.head_of_number),
                modifier = Modifier.padding(start = 14.dp),
                color = Color.Black
            )
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = defaultLightGray,
            focusedBorderColor = selectedDarkGray,
            textColor = primaryDark,
            errorBorderColor = errorRed,
            focusedLabelColor = primaryDark,
            cursorColor = mainGreen,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            visualTransformation = MaskVisualTransformation(Formats.PHONE_MASK) ,
        isError = isError,
    )
    Column(modifier = modifier) {
        if (isError) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}