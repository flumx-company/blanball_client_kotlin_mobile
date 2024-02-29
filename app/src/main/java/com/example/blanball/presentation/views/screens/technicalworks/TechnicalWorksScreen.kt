package com.example.blanball.presentation.views.screens.technicalworks

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import kotlinx.coroutines.delay

@Composable
fun TechnicalWorksScreen(
    messageTextId: Int,
    secondaryTextId: Int,
) {
    val rotationAnim = remember {
        Animatable(initialValue = 0f,)
    }
    LaunchedEffect(Unit) {
        rotationAnim.animateTo(
            targetValue = 90f,
            animationSpec = repeatable(
                iterations = 1,
                animation = tween(durationMillis = 2000),
                repeatMode = RepeatMode.Restart
            ),
        )
        delay(2000)
    }

    Box {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter)
                .offset(y = (32).dp),
            painter = painterResource(id = R.drawable.tech_works_bg),
            contentDescription = null
        )
        Column(modifier = Modifier.padding(start = 16.dp, top = 20.dp, end = 16.dp)) {

            Text(
                text = stringResource(messageTextId).uppercase(),
                fontSize = 20.sp,
                lineHeight = 28.sp,
                style = typography.h3,
                fontWeight = FontWeight(800),
                color = primaryDark,
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = stringResource(secondaryTextId),
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = secondaryNavy,
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .offset(
                            x = (-40).dp,
                            y = ((35).dp)
                        )
                        .rotate(rotationAnim.value),
                    painter = painterResource(R.drawable.gear_2),
                    contentDescription = null,
                )
                Image(
                    modifier = Modifier
                        .offset(
                            x = 40.dp,
                            y = ((-5).dp)
                        )
                        .rotate(rotationAnim.value),
                    painter = painterResource(R.drawable.gear_1),
                    contentDescription = null,
                )
                Image(
                    modifier = Modifier
                        .offset(
                            x = (-40).dp,
                            y = ((-35).dp)
                        )
                        .rotate(rotationAnim.value),
                    painter = painterResource(R.drawable.gear_3),
                    contentDescription = null,
                )
            }
        }
    }
}