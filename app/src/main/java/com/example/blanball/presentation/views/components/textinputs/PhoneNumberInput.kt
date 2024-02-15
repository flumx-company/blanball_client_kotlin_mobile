package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.borderPrimary
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.MaskVisualTransformation
import com.example.domain.utils.Formats

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhoneNumberInput(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    modifier: Modifier,
    textFieldModifier: Modifier = Modifier.height(44.dp).padding(top = 5.dp).fillMaxWidth(),
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    errorMessage: String = "",
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isTextFieldFocused by interactionSource.collectIsFocusedAsState()
    Column(modifier = modifier.animateContentSize().fillMaxSize()) {
    BasicTextField(
        modifier = textFieldModifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        interactionSource = interactionSource,
        textStyle = TextStyle(
            color = primaryDark,
            fontSize = 13.sp,
            fontWeight = FontWeight(400),
            fontFamily = FontFamily(Font(R.font.inter)),
            lineHeight = 24.sp,
            textMotion = TextMotion.Animated,
        ),
        visualTransformation = MaskVisualTransformation(Formats.PHONE_MASK),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
    ) { innerTextField ->
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = value,
            innerTextField = innerTextField,
            singleLine = true,
            leadingIcon = {
                Text(
                    text = stringResource(id = R.string.head_of_number),
                    modifier = Modifier.padding(start = 14.dp),
                    color = Color.Black
                )
            },
            border = {
                Box(
                    modifier = Modifier.border(
                        width = 1.dp,
                        color = (
                                when {
                                    (isTextFieldFocused && !isError) -> selectedDarkGray
                                    (isError) -> errorRed
                                    else -> borderPrimary
                                }),
                        shape = shapes.medium
                    )
                )
            },
            visualTransformation = VisualTransformation.None,
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.textFieldWithLabelPadding(
                top = 0.dp, bottom = 0.dp
            ),
            isError = isError,
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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = defaultLightGray,
                focusedBorderColor = selectedDarkGray,
                textColor = primaryDark,
                errorBorderColor = errorRed,
                focusedLabelColor = primaryDark,
                cursorColor = mainGreen,
            ),
            enabled = true,
    )
    }
        if (isError) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}