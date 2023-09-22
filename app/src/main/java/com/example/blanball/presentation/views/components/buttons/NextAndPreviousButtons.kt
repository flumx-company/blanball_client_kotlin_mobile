package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun NextAndPreviousButtonsVertical (
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

@Composable
fun NextAndPreviousButtonsHorizontal (
    isEnabled: Boolean,
    nextBtnOnClick: () -> Unit,
    prevBtnOnClick: () -> Unit,
    nextBtnOnTextId: Int,
    prevBtnOnTextId: Int,
    cancelButtonColor: Color = secondaryNavy,
    borderCancelButtonColor: Color = defaultLightGray
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Button(
            onClick = prevBtnOnClick,
            Modifier
                .weight(1f)
                .height(40.dp),
            shape = shapes.medium,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = cancelButtonColor,
            ),
            border = BorderStroke(
                    width = 1.dp,
            color = borderCancelButtonColor,)
        ) {
            Text(
                text = stringResource(id = prevBtnOnTextId),
                style = typography.h4,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = cancelButtonColor,
                fontWeight = FontWeight(500),
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Button(
            enabled = isEnabled,
            onClick = nextBtnOnClick,
            modifier = Modifier
                .weight(1f)
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
    }
}

