package com.example.blanball.presentation.views.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.typography

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainGreen),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.size(200.dp))
            Image(
                modifier = Modifier.size(121.dp),
                painter = painterResource(id = R.drawable.logo_green),
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.blanball),
                style = typography.h3,
                fontSize = 54.sp,
                lineHeight = 80.sp,
                fontWeight = FontWeight(700),
                color = Color.White,
            )
        }
    }
}