package com.example.blanball.presentation.views.screens.onboarding

import OutlineRadioButton
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.textinputs.ReadOnlyOutlinePlaceholder

@Composable
fun FillingOutTheUserProfileScreenStep2 (
    state: UiState,
    onFillingOutTheUserProfileStep3Clicked: () -> Unit,
    onTurnBackClicked: () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    val currentState: OnboardingScreensStatesMainContract.State =
        (state as? OnboardingScreensStatesMainContract.State)
            ?: OnboardingScreensStatesMainContract.State(
                OnboardingScreensStatesMainContract.ScreenViewState.Idle
            )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? OnboardingScreensStatesMainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.onboard_2_bg),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedPaddingCard {
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
                        textAlign = TextAlign.Center,
                    )
                    Row(
                        Modifier.padding(top = 20.dp)
                    ) {
                        repeat(2) {
                            Image(
                                painter = painterResource(R.drawable.stepline_1),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.size(2.dp))
                        }
                        repeat(2) {
                            Image(
                                painter = painterResource(id = R.drawable.empty_stepline),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(
                        text = stringResource(id = R.string.what_about_your_background),
                        style = typography.h3,
                        color = secondaryNavy
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = stringResource(id = R.string.have_you_played_football_before),
                        style = typography.h5,
                        color = primaryDark
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlineRadioButton(
                            modifier = Modifier.weight(1f),
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
                            modifier = Modifier.weight(1f),
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
                            modifier = Modifier.weight(1f),
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
                            modifier = Modifier.weight(1f),
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
                        color = primaryDark
                    )
                    ReadOnlyOutlinePlaceholder(
                        modifier = Modifier.fillMaxWidth(1f),
                        value = it.selectDocumentState.value,
                        onValueChange = { state.selectDocumentState.value = it },
                        labelResId = R.string.document,
                        trailingIconRedId = R.drawable.ic_clip
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        enabled = it.footballQualificationsState.value != OnboardingScreensStatesMainContract.FootballQualificationsState.NO_SELECT,
                        onClick = onFillingOutTheUserProfileStep3Clicked,
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
                        onClick = onTurnBackClicked,
                        Modifier
                            .padding(top = 14.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(id = R.string.turn_back),
                            style = typography.h4,
                        )
                    }
                }
            }
        }
    }
}
