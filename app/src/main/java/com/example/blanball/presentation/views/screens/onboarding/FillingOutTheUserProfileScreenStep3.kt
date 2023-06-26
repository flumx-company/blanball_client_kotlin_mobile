package com.example.blanball.presentation.views.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput

@Composable
fun FillingOutTheUserProfileScreenStep3(
    state: UiState,
    onFillingOutTheUserProfileStep4Clicked: () -> Unit,
    onTurnBackClicked: () -> Unit,
) {
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
                painter = painterResource(id = R.drawable.onboard_bg_3),
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
                        text = stringResource(R.string.sports_characteristics),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = primaryDark,
                        textAlign = TextAlign.Center,
                    )
                    Row(
                        Modifier.padding(top = 20.dp).align(Alignment.CenterHorizontally)
                    ) {
                        repeat(3) {
                            Image(
                                painter = painterResource(R.drawable.stepline_1),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.size(2.dp))
                        }
                        Image(
                            painter = painterResource(id = R.drawable.empty_stepline),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(
                        text = stringResource(id = R.string.physical_parameters),
                        style = typography.h5,
                        color = primaryDark
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        DefaultTextInput(
                            modifier = Modifier.weight(0.5f).fillMaxWidth(),
                            state = it,
                            labelResId = R.string.height,
                            value = it.heightState.value,
                            onValueChange = { state.heightState.value = it },
                            transformation = VisualTransformation.None,
                        )
                        DefaultTextInput(
                            state = it,
                            labelResId = R.string.weight,
                            value = it.weightState.value,
                            onValueChange = { state.weightState.value = it },
                            transformation = VisualTransformation.None,
                            modifier = Modifier.weight(0.5f).fillMaxWidth()
                        )
                        CustomDropDownMenu(
                            labelResId = R.string.kicking_leg,
                            listItems = listOf(
                                stringResource(id = R.string.right_leg),
                                stringResource(id = R.string.left_leg)
                            ),
                            value = it.workingLegState.value,
                            onValueChange = { state.workingLegState.value = it },
                            modifier = Modifier.weight(1f).fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = stringResource(id = R.string.choose_your_playing_position),
                        style = typography.h5,
                        color = primaryDark
                    )
                    CustomDropDownMenu(
                        labelResId = R.string.your_playing_position,
                         modifier = Modifier.fillMaxWidth(),
                        listItems = listOf(
                            stringResource(id = R.string.any_position),
                            stringResource(id = R.string.goalkeeper),
                            stringResource(id = R.string.right_defender),
                            stringResource(id = R.string.left_defender),
                            stringResource(id = R.string.central_defender),
                            stringResource(id = R.string.left_flank_defender),
                            stringResource(id = R.string.right_flank_defender),
                            stringResource(id = R.string.supporting_mid_defender),
                            stringResource(id = R.string.left_mid_defender),
                            stringResource(id = R.string.attacking_mid_defender),
                            stringResource(id = R.string.right_winger),
                            stringResource(id = R.string.left_winger),
                            stringResource(id = R.string.right_flank_attacker),
                            stringResource(id = R.string.left_flank_attacker),
                            stringResource(id = R.string.central_forward),
                            stringResource(id = R.string.left_forward),
                            stringResource(id = R.string.right_forward),
                            stringResource(id = R.string.forward_striker),
                        ),
                        value = it.positionState.value ,
                        onValueChange = {state.positionState.value = it}
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        onClick = onFillingOutTheUserProfileStep4Clicked,
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