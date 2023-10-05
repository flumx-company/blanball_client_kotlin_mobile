package com.example.blanball.presentation.views.components.dropdownmenu

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomDropDownMenu(
    columnModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
    labelResId: Int,
    listItems: List<String>,
    value: String,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    errorMessage: String = "",
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(columnModifier.animateContentSize()) {
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    readOnly = true,
                    textStyle = typography.h6,
                    modifier = Modifier.fillMaxWidth(1f),
                    label = {
                        Text(
                            text = stringResource(id = labelResId),
                            style = typography.h6,
                            color = primaryDark,
                            maxLines = 1,
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(
                                id = if (expanded) R.drawable.ic_top else R.drawable.ic_down
                            ),
                            contentDescription = null,
                            tint = secondaryNavy,
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = defaultLightGray,
                        focusedBorderColor = mainGreen,
                        textColor = Color.Black,
                        errorBorderColor = errorRed,
                        focusedLabelColor = primaryDark,
                        cursorColor = mainGreen,
                    ),
                    isError = isError,
                )
                if (isError) {
                    Text(text = errorMessage, style = typography.h6, color = errorRed)
                }
            }

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                listItems.forEach { selectedOption ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        onValueChange(selectedOption)
                    }) {
                        Text(
                            text = selectedOption, style = typography.h6, color = primaryDark
                        )
                    }
                }
            }
    }
}
}