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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PassTextInput(
    labelResId: Int,
    value: String,
    onValueChange: (String) -> Unit,
    state: StartScreensMainContract.State,
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier.padding(top = 5.dp).height(44.dp).fillMaxWidth(),
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    errorMessage: String = "",
    visibilityIconState: MutableState<Boolean>,
    interactionSource: MutableInteractionSource = MutableInteractionSource(),
) {
    val interactionSource by remember { mutableStateOf(interactionSource) }
    val isTextFieldFocused by interactionSource.collectIsFocusedAsState()
    Column(
        modifier = modifier.animateContentSize().fillMaxSize()
    ) {
        BasicTextField(
            modifier = textFieldModifier,
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = if (visibilityIconState.value) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = TextStyle(
                color = primaryDark,
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                fontFamily = FontFamily(Font(R.font.inter)),
                lineHeight = 24.sp,
                textMotion = TextMotion.Animated,
            ),
        ) { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value,
                innerTextField = innerTextField,
                trailingIcon = {
                    IconButton(onClick = {
                        visibilityIconState.value = !visibilityIconState.value
                    }) {
                        Icon(
                            painter = painterResource(id = if (visibilityIconState.value) R.drawable.ic_eye_on else R.drawable.ic_eye_off),
                            tint = secondaryNavy,
                            contentDescription = null,
                        )
                    }
                },
                contentPadding = TextFieldDefaults.textFieldWithLabelPadding(
                    top = 0.dp, bottom = 0.dp
                ),
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
                visualTransformation = if (visibilityIconState.value) VisualTransformation.None else PasswordVisualTransformation(),
                interactionSource = interactionSource,
                isError = isError,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = primaryDark,
                    errorBorderColor = errorRed,
                    focusedLabelColor = primaryDark,
                    cursorColor = mainGreen,
                ),
                singleLine = true,
                enabled = true,
            )
        }
        if (isError) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}
