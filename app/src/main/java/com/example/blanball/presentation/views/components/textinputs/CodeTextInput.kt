package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.unit.dp
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : UiState> CodeTextInput(
    state: T,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    errorMessage: String = "",
    isError: Boolean? = null,
    enabled: Boolean = true,
    codeText: (T) -> List<MutableState<String>>,
    onCodeChange: (T, Int, String) -> Unit,
    onDoneAction: () -> Unit = {},
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isTextFieldFocused by interactionSource.collectIsFocusedAsState()
    val localFocusManager = LocalFocusManager.current
    val focusRequesters = remember {
        List(5) { FocusRequester() }
    }
    Column(modifier = modifier.animateContentSize().fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(5) { i ->
                BasicTextField(
                    modifier = textFieldModifier
                        .weight(1f)
                        .height(44.dp)
                        .fillMaxWidth()
                        .onKeyEvent {
                            if (it.key == Key.Backspace && i > 0) {
                                focusRequesters[i - 1].requestFocus()
                            }
                            true
                        }
                        .focusRequester(focusRequesters[i])
                        .padding(
                            end = when (i) {
                                in 0..3 -> 6.dp
                                else -> 0.dp
                            }
                        ),
                    value = codeText(state)[i].value,
                    onValueChange = { newValue ->
                        onCodeChange(state, i, newValue)
                        when {
                            newValue.length == 1 && i < 4 -> focusRequesters[i + 1].requestFocus()
                        }
                    },
                    singleLine = true,
                    interactionSource = interactionSource,
                    enabled = enabled,
                    textStyle = TextStyle(
                        color = primaryDark,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.inter)),
                        lineHeight = 24.sp,
                        textMotion = TextMotion.Animated,
                        textAlign = TextAlign.Center,
                    ),
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(
                        imeAction = when (i) {
                            4 -> ImeAction.Done
                            else -> ImeAction.Next
                        }
                    ),
                    keyboardActions = when (i) {
                        4 -> KeyboardActions(onDone = { localFocusManager.clearFocus(); onDoneAction() })
                        else -> KeyboardActions.Default
                    },
                ) { innerTextField ->

                    TextFieldDefaults.OutlinedTextFieldDecorationBox(
                        innerTextField = innerTextField,
                        enabled = enabled,
                        value = codeText(state)[i].value,
                        border = {
                            Box(
                                modifier = Modifier.border(
                                    width = 1.dp,
                                    color = (
                                            when {
                                                (isTextFieldFocused && !isError!!) -> selectedDarkGray
                                                (isError == true) -> errorRed
                                                else -> defaultLightGray
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
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = defaultLightGray,
                            focusedBorderColor = selectedDarkGray,
                            textColor = primaryDark,
                            errorBorderColor = errorRed,
                            focusedLabelColor = primaryDark,
                            cursorColor = mainGreen,
                        ),
                        singleLine = true,
                    )
                }
            }
        }
        if (isError == true) {
            Text(text = errorMessage, style = typography.h6, color = errorRed)
        }
    }
}