package com.example.blanball.presentation.views.screens.onboarding.usertraining

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard

@Composable
fun UserTrainingStep3(
    onTrainingUserStep4Clicked: () -> Unit,
    onSkipButtonClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_training_user_step_3),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        AnimatedPaddingCard ({
            Column(
                modifier = Modifier
                    .padding(
                        top = 28.dp,
                        start = 16.dp,
                        bottom = 30.dp,
                        end = 16.dp,
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(R.string.influence_the_quality_of_events),
                    modifier = Modifier.fillMaxWidth(),
                    style = typography.h2,
                    color = secondaryNavy,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(id = R.string.after_each_event),
                    style = typography.h3,
                    lineHeight = 20.sp,
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Box(modifier = Modifier.background(bgLight, RoundedCornerShape(size = 6.dp))) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_attention),
                            contentDescription = null,
                            tint = secondaryNavy,
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = stringResource(id = R.string.users_with_good_rating),
                            style = typography.h6,
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(24.dp))
                Button(
                    onClick = onTrainingUserStep4Clicked,
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
                        text = stringResource(id = R.string.next),
                        style = typography.h4,
                    )
                }
                TextButton(
                    onClick = onSkipButtonClicked,
                    Modifier
                        .padding(top = 14.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        style = typography.h4,
                        color = secondaryNavy,
                    )
                }
            }
        },
            enableAnimation = false,
        )
    }
}