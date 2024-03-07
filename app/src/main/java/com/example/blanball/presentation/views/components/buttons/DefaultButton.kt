package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    btnTextResId: Int,
    clickCallback: () -> Unit,
){
    Box(
       modifier = modifier
           .fillMaxWidth()
           .background(color = mainGreen, shape = shapes.medium)
           .clickable {
               clickCallback()
           }
           .padding(vertical = 10.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(btnTextResId),
            fontWeight = FontWeight(400),
            lineHeight = 24.sp,
            fontSize = 15.sp,
            style = typography.h4,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
    }
}