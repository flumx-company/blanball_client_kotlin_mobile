package com.example.blanball.presentation.views.components.textinputs

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography

@Composable
fun ReadOnlyOutlinePlaceholder(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelResId: Int,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        singleLine = true,
        onValueChange = onValueChange,
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
                text = stringResource(id = labelResId),
                color = primaryDark,
                style = typography.h6,
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                lineHeight = 16.sp,
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