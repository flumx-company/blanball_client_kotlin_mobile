package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@Composable
fun ReadOnlyOutlinePlaceholder(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    trailingIconResId: Int,
    labelResId: Int,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
        label = {
            Text(
                text = stringResource(id = labelResId),
                color = primaryDark,
                style = typography.h6,
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
            )
        },
        trailingIcon = {
            Icon(
                painter = painterResource(id = trailingIconResId),
                contentDescription = null,
                tint = secondaryNavy
            )
        },
        readOnly = true,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = defaultLightGray,
            focusedBorderColor = mainGreen,
            textColor = primaryDark,
            errorBorderColor = errorRed,
            focusedLabelColor = primaryDark,
            cursorColor = mainGreen,
        )
    )
}