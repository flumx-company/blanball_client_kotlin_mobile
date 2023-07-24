package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun NextAndPreviousButtons (
    isEnabled: Boolean,
    nextBtnOnClick: () -> Unit,
    prevBtnOnClick: () -> Unit,
    nextBtnOnTextId: Int,
    prevBtnOnTextId: Int,
        ) {
    Column() {
    Button(
        enabled = isEnabled,
        onClick = nextBtnOnClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = shapes.medium,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = mainGreen,
            contentColor = Color.White,
        ),
    ) {
        Text(
            text = stringResource(id = nextBtnOnTextId),
            style = typography.h4,
            lineHeight = 24.sp,
            fontWeight = FontWeight(400),
        )
    }
        TextButton(
            onClick = prevBtnOnClick,
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 14.dp)
        ) {
            Text(
                text = stringResource(id = prevBtnOnTextId),
                style = typography.h4,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = secondaryNavy,
                fontWeight = FontWeight(500),
            )
        }
    }
}