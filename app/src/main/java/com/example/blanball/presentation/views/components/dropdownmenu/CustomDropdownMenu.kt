package com.example.blanball.presentation.views.components.dropdownmenu

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    textFieldModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    labelResId: Int,
    listItems: List<String>,
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    errorMessage: String = "",
    isFirstElementSelected: Boolean = false,
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isTextFieldFocused by interactionSource.collectIsFocusedAsState()

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            if (!isFirstElementSelected) expanded = !expanded
        },
    ) {
        Row(
            verticalAlignment = CenterVertically,
        ) {
            Column(
                Modifier.fillMaxWidth().animateContentSize(),
                verticalArrangement = Arrangement.Center
            ) {
                BasicTextField(
                    modifier = textFieldModifier.padding(top = 5.dp).height(44.dp).fillMaxWidth(),
                    value = value,
                    onValueChange = onValueChange,
                    singleLine = true,
                    readOnly = true,
                    interactionSource = interactionSource,
                    enabled = !isFirstElementSelected,
                    textStyle = TextStyle(
                        color = if (isFirstElementSelected) selectedDarkGray else primaryDark,
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
                        enabled = true,
                        singleLine = true,
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
                        trailingIcon = {
                            Icon(
                                painter = painterResource(
                                    id = if (expanded) R.drawable.dropdown_arrow_up else R.drawable.dropdown_arrow_down
                                ),
                                contentDescription = null,
                                tint = if (isFirstElementSelected) selectedDarkGray else primaryDark,
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
                                    id = labelResId
                                ),
                                color = if (isFirstElementSelected) selectedDarkGray else primaryDark,
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
                AnimatedVisibility(
                    visible = expanded
                ) {
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { !expanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listItems.forEach { selectedOption ->
                            DropdownMenuItem(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    expanded = !expanded
                                    onValueChange(selectedOption)
                                }) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = selectedOption,
                                    style = typography.h6,
                                    color = primaryDark
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}