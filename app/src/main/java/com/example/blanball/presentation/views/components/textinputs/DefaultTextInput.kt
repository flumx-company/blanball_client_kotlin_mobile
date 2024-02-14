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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DefaultTextInput(
    modifier: Modifier = Modifier.fillMaxWidth(),
    textFieldModifier: Modifier = Modifier.padding(top = 5.dp).height(44.dp).fillMaxWidth(),
    labelResId: Int,
    state: UiState,
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    transformation: VisualTransformation,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMessage: String = "",
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    isSingleLine: Boolean = true,
    readOnly: Boolean = false,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
) {
    val interactionSource by remember { mutableStateOf(interactionSource) }
    val isTextFieldFocused by interactionSource.collectIsFocusedAsState()
    Column(
        modifier = modifier.animateContentSize().fillMaxSize()
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = textFieldModifier,
            singleLine = isSingleLine,
            interactionSource = interactionSource,
            enabled = enabled,
            visualTransformation = transformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            readOnly = readOnly,
        ) { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = isSingleLine,
                leadingIcon = leadingIcon,
                border = {
                    Box(
                        modifier = Modifier.border(
                            width = 1.dp,
                            color = (
                                    when {
                                        (isTextFieldFocused && !isError) -> selectedDarkGray
                                        (isError) -> errorRed
                                        else -> defaultLightGray
                                    }),
                            shape = shapes.medium
                        )
                    )
                },
                trailingIcon = trailingIcon,
                visualTransformation = VisualTransformation.None,
                interactionSource = interactionSource,
                contentPadding = TextFieldDefaults.textFieldWithLabelPadding(
                    top = 0.dp, bottom = 0.dp
                ),
                isError = isError,
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
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = primaryDark,
                    errorBorderColor = errorRed,
                    focusedLabelColor = primaryDark,
                    cursorColor = mainGreen,
                )
            )
        }
        if (isError) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}