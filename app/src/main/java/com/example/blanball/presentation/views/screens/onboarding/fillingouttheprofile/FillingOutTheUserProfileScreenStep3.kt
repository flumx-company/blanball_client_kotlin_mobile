package com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.blanball.utils.ext.isNotValidHeight
import com.example.blanball.utils.ext.isNotValidWeight
import com.example.blanball.utils.ext.isValidHeight
import com.example.blanball.utils.ext.isValidWeight

@Composable
fun FillingOutTheUserProfileScreenStep3(
    state: UiState,
    onFillingOutTheUserProfileStep4Clicked: () -> Unit,
    onTurnBackClicked: () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
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
                        Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.CenterHorizontally)
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
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxWidth(),
                            state = it,
                            labelResId = R.string.height,
                            value = it.heightState.value,
                            onValueChange = { state.heightState.value = it },
                            transformation = VisualTransformation.None,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                            isError = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> true
                                it.heightState.value.isNotValidHeight() ->  true
                                else -> false
                            },
                            errorMessage = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                                it.heightState.value.isNotValidHeight() ->  stringResource(id = R.string.height_valid_error)
                                else -> {("")}
                            }
                        )
                        DefaultTextInput(
                            modifier = Modifier
                                .weight(0.5f)
                                .fillMaxWidth(),
                            state = it,
                            labelResId = R.string.weight,
                            value = it.weightState.value,
                            onValueChange = { state.weightState.value = it },
                            transformation = VisualTransformation.None,
                            keyboardOptions = KeyboardOptions.Default.copy( imeAction =  ImeAction.Done, keyboardType = KeyboardType.Number),
                            keyboardActions = KeyboardActions(onDone = {localFocusManager.clearFocus()}),
                            isError = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> true
                                it.weightState.value.isNotValidWeight() ->  true
                                else -> false
                            },
                            errorMessage = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                                it.weightState.value.isNotValidWeight() ->  stringResource(id = R.string.weight_valid_error)
                                else -> {("")}
                            }
                        )
                        CustomDropDownMenu(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            labelResId = R.string.kicking_leg,
                            listItems = listOf(
                                stringResource(id = R.string.right_leg),
                                stringResource(id = R.string.left_leg)
                            ),
                            value = it.workingLegState.value,
                            onValueChange = { state.workingLegState.value = it },
                            isError = when {
                                it.workingLegState.value.isEmpty() -> true
                                else -> false
                            },
                            errorMessage = when {
                                it.workingLegState.value.isEmpty() -> stringResource(id = R.string.work_leg_valid_error)
                                else -> {("")}
                            }
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
                        onValueChange = {state.positionState.value = it},
                        isError = when {
                            it.positionState.value.isEmpty() -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.positionState.value.isEmpty() -> stringResource(id = R.string.position_valid_error)
                            else -> {("")}
                        }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        enabled = it.heightState.value.isValidHeight()
                                && it.weightState.value.isValidWeight()
                                && it.positionState.value.isNotEmpty()
                                && it.workingLegState.value.isNotEmpty(),
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