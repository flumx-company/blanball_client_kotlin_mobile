package com.example.blanball.presentation.views.screens.fourhundredandfourth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun FourHundredAndFourthScreen(
//    navigateToHomeScreen: () -> Unit, TODO()
) {
    Box {
        Column(
            modifier = Modifier.padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.ic_404),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                modifier = Modifier.width(288.dp),
                text = stringResource(R.string.no_such_page_exists),
                fontSize = 24.sp,
                lineHeight = 28.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(40.dp)
                    .width(288.dp),
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = mainGreen,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = stringResource(R.string.to_the_home_screen),
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}