package com.example.blanball.presentation.views.screens.onboarding.usertraining

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.blanball.R
import com.example.blanball.presentation.theme.backgroundGradient

@Composable
fun UserTrainingStep4(
    onFillingOutTheUserProfileStartScreenClicked: () -> Unit,
    onSkipButtonClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.bg_training_user_step_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
//        AnimatedPaddingCard {
//            Column(
//                modifier = Modifier
//                    .padding(
//                        top = 28.dp,
//                        start = 16.dp,
//                        bottom = 30.dp,
//                        end = 16.dp,
//                    )
//                    .verticalScroll(rememberScrollState())
//            ) {
//                Text(
//                    text = stringResource(R.string.fill_out_your_profile_and_start_looking_for_a_team),
//                    modifier = Modifier.fillMaxWidth(),
//                    style = typography.h2,
//                    color = primaryDark,
//                    textAlign = TextAlign.Center,
//                )
//                Spacer(modifier = Modifier.size(12.dp))
//                Text(
//                    text = stringResource(id = R.string.blanball_is),
//                    style = typography.h3,
//                    color = secondaryNavy,
//                )
//                Spacer(modifier = Modifier.weight(1f))
//                Spacer(modifier = Modifier.size(24.dp))
//                Button(
//                    onClick = onFillingOutTheUserProfileStartScreenClicked,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(40.dp),
//                    shape = shapes.medium,
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = mainGreen,
//                        contentColor = Color.White,
//                    ),
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.to_filling_out_the_profile),
//                        style = typography.h4,
//                    )
//                }
//                TextButton(
//                    onClick = onSkipButtonClicked,
//                    Modifier
//                        .padding(top = 14.dp)
//                        .align(Alignment.CenterHorizontally)
//                ) {
//                    Text(
//                        text = stringResource(id = R.string.skip),
//                        style = typography.h4,
//                    )
//                }
//            }
//        }
    }
}