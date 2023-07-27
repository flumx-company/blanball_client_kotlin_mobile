package com.example.blanball.presentation.views.screens.createnewevent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.successValidationGreenBG

@Composable
fun CreateNewEventScreen(
    paddingValues: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(successValidationGreenBG)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "TODO() Create New Event Screen in Future",
            fontWeight = FontWeight.Bold,
            color = secondaryNavy,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}