package com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsVertical
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard

@Composable
fun FillingOutTheUserProfileStartScreen(
    state: UiState,
    onFillingOutTheUserProfileStep1Clicked: () -> Unit,
    onRemindMeLater: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? OnboardingScreensStatesMainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.onboard_start_bg),
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
                        .verticalScroll(rememberScrollState()),
                ) {
                    Text(
                        text = stringResource(R.string.prepare_your_profile),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = secondaryNavy,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(
                        text = stringResource(id = R.string.tell_us_about_yourself),
                        style = typography.h3,
                        lineHeight = 20.sp,
                        fontSize = 16.sp,
                        color = secondaryNavy
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    NextAndPreviousButtonsVertical(
                        isEnabled = true,
                        nextBtnOnClick = onFillingOutTheUserProfileStep1Clicked,
                        prevBtnOnClick = onRemindMeLater,
                        nextBtnOnTextId = R.string.set_up ,
                        prevBtnOnTextId = R.string.remind_me_later,
                    )
                }
            },
                enableAnimation = false
            )
        }
    }
}