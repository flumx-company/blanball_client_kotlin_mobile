package com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile

import OutlineRadioButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsVertical
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.textinputs.ReadOnlyOutlinePlaceholder

@Composable
fun FillingOutTheUserProfileScreenStep2 (
    state: UiState,
    onFillingOutTheUserProfileStep3Clicked: () -> Unit,
    onTurnBackClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? OnboardingScreensStatesMainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.onboard_2_bg),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedPaddingCard(
                {
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
                            text = stringResource(R.string.your_sports_skills),
                            modifier = Modifier.fillMaxWidth(),
                            style = typography.h2,
                            color = primaryDark,
                            textAlign = TextAlign.Left,
                            fontSize = 23.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight(700),
                        )
                        Row(
                            Modifier.padding(top = 20.dp)
                        ) {
                            repeat(2) {
                                Image(
                                    painter = painterResource(R.drawable.stepline_1),
                                    contentDescription = null,
                                    Modifier
                                        .weight(1f)
                                        .height(4.dp),
                                )
                                Spacer(modifier = Modifier.size(2.dp))
                            }
                            repeat(2) {
                                Image(
                                    painter = painterResource(id = R.drawable.empty_stepline),
                                    contentDescription = null,
                                    Modifier
                                        .weight(1f)
                                        .height(4.dp),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.size(24.dp))
                        Text(
                            text = stringResource(id = R.string.have_you_played_football_before),
                            style = typography.h5,
                            color = primaryDark,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(700),
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlineRadioButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        it.footballQualificationsState.value =
                                            OnboardingScreensStatesMainContract.FootballQualificationsState.INDEPENDENTLY
                                    },
                                onClick = {
                                    it.footballQualificationsState.value =
                                        OnboardingScreensStatesMainContract.FootballQualificationsState.INDEPENDENTLY
                                },
                                state = it,
                                text = stringResource(id = R.string.independently),
                                selected = it.footballQualificationsState.value == OnboardingScreensStatesMainContract.FootballQualificationsState.INDEPENDENTLY,
                                icon = null,
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            OutlineRadioButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        it.footballQualificationsState.value =
                                            OnboardingScreensStatesMainContract.FootballQualificationsState.PROFESSIONALLY
                                    },
                                onClick = {
                                    it.footballQualificationsState.value =
                                        OnboardingScreensStatesMainContract.FootballQualificationsState.PROFESSIONALLY
                                },
                                state = it,
                                text = stringResource(id = R.string.professionally),
                                selected = it.footballQualificationsState.value == OnboardingScreensStatesMainContract.FootballQualificationsState.PROFESSIONALLY,
                                icon = null,
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            OutlineRadioButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        it.footballQualificationsState.value =
                                            OnboardingScreensStatesMainContract.FootballQualificationsState.AMATEURISH
                                    },
                                onClick = {
                                    it.footballQualificationsState.value =
                                        OnboardingScreensStatesMainContract.FootballQualificationsState.AMATEURISH
                                },
                                state = it,
                                text = stringResource(id = R.string.amateurish),
                                selected = it.footballQualificationsState.value == OnboardingScreensStatesMainContract.FootballQualificationsState.AMATEURISH,
                                icon = null,
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            OutlineRadioButton(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        it.footballQualificationsState.value =
                                            OnboardingScreensStatesMainContract.FootballQualificationsState.DID_N0T_PRACTICE
                                    },
                                onClick = {
                                    it.footballQualificationsState.value =
                                        OnboardingScreensStatesMainContract.FootballQualificationsState.DID_N0T_PRACTICE
                                },
                                state = it,
                                text = stringResource(id = R.string.didnt_practice),
                                selected = it.footballQualificationsState.value == OnboardingScreensStatesMainContract.FootballQualificationsState.DID_N0T_PRACTICE,
                                icon = null,
                            )
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        Text(
                            text = stringResource(id = R.string.confirm_your_qualifications_with_a_document),
                            style = typography.h5,
                            color = primaryDark,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(700),
                        )
                        ReadOnlyOutlinePlaceholder(
                            modifier = Modifier.fillMaxWidth(1f),
                            value = it.selectDocumentState.value,
                            onValueChange = { state.selectDocumentState.value = it },
                            labelResId = R.string.document,
                            trailingIconResId = R.drawable.ic_clip
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.size(24.dp))
                        NextAndPreviousButtonsVertical(
                            isEnabled = it.footballQualificationsState.value != OnboardingScreensStatesMainContract.FootballQualificationsState.NO_SELECT,
                            nextBtnOnClick = onFillingOutTheUserProfileStep3Clicked,
                            prevBtnOnClick = onTurnBackClicked,
                            nextBtnOnTextId = R.string.next,
                            prevBtnOnTextId = R.string.turn_back,
                        )
                    }
                },
                enableAnimation = false,
            )
        }
    }
}
