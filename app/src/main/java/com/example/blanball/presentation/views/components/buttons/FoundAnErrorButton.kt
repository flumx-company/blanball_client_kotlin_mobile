package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun FoundAnErrorButton(
    buttonClickCallback: () -> Unit
) {
        Button(
            onClick = { buttonClickCallback() },
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            shape = shapes.medium,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = secondaryNavy,
                contentColor = Color.White,
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = null,
                tint = Color.White,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(id = R.string.found_an_error),
                style = typography.h4,
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(500),
                textAlign = TextAlign.Center,
            )
        }
    }