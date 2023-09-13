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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsVertical
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.textinputs.BottomLineDefaultTextInput
import com.example.blanball.utils.ext.isNotValidBirthDay
import com.example.blanball.utils.ext.isNotValidBirthMonth
import com.example.blanball.utils.ext.isNotValidBirthYear
import com.example.blanball.utils.ext.isValidBirthDay
import com.example.blanball.utils.ext.isValidBirthMonth
import com.example.blanball.utils.ext.isValidBirthYear

@Composable
fun FillingOutTheUserProfileScreenStep1(
    state: UiState,
    onFillingOutTheUserProfileStep2Clicked: () -> Unit,
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
                painter = painterResource(id = R.drawable.onboard_bg),
                contentDescription = null,
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
                        text = stringResource(R.string.lets_meet),
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
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                        repeat(3) {
                            Spacer(modifier = Modifier.size(2.dp))
                            Image(
                                painter = painterResource(id = R.drawable.empty_stepline),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(
                        text = stringResource(id = R.string.when_were_you_born),
                        style = typography.h5,
                        color = primaryDark
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        BottomLineDefaultTextInput(
                            labelResId = R.string.day,
                            modifier = Modifier.weight(1f),
                            value = it.dayBirthdayState.value,
                            onValueChange = { state.dayBirthdayState.value = it },
                            state = it,
                            transformation = VisualTransformation.None,
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                            isError = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> true
                                it.dayBirthdayState.value.isNotValidBirthDay() -> true
                                else -> false
                            },
                            errorMessage = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                                it.dayBirthdayState.value.isNotValidBirthDay() -> stringResource(id = R.string.birth_day_valid_error)
                                else -> {("")}
                            }
                        )
                        BottomLineDefaultTextInput(
                            labelResId = R.string.month,
                            value = it.monthBirthdayState.value,
                            modifier = Modifier.weight(1f),
                            state = it,
                            transformation = VisualTransformation.None,
                            onValueChange = { state.monthBirthdayState.value = it },
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                            isError = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> true
                                it.monthBirthdayState.value.isNotValidBirthMonth() -> true
                                else -> false
                            },
                            errorMessage = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                                it.monthBirthdayState.value.isNotValidBirthMonth() -> stringResource(id = R.string.birth_month_valid_error)
                                else -> {("")}
                            }
                        )
                        BottomLineDefaultTextInput(
                            labelResId = R.string.year,
                            state = it,
                            modifier = Modifier.weight(1f),
                            transformation = VisualTransformation.None,
                            value = it.yearBirthdayState.value,
                            onValueChange = { state.yearBirthdayState.value = it },
                            keyboardOptions = KeyboardOptions.Default.copy( imeAction =  ImeAction.Done, keyboardType = KeyboardType.Number),
                            keyboardActions = KeyboardActions(onDone = {localFocusManager.clearFocus()}),
                            isError = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> true
                                it.yearBirthdayState.value.isNotValidBirthYear() -> true
                                else -> false
                            },
                            errorMessage = when {
                                it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                                it.yearBirthdayState.value.isNotValidBirthYear() -> stringResource(id = R.string.birth_year_valid_error)
                                else -> {("")}
                            }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    NextAndPreviousButtonsVertical(
                        isEnabled = it.dayBirthdayState.value.isValidBirthDay()
                                && it.monthBirthdayState.value.isValidBirthMonth()
                                && it.yearBirthdayState.value.isValidBirthYear(),
                        nextBtnOnClick = onFillingOutTheUserProfileStep2Clicked,
                        prevBtnOnClick = onTurnBackClicked,
                        nextBtnOnTextId = R.string.next,
                        prevBtnOnTextId = R.string.turn_back,
                    )
                }
            },
                enableAnimation = false
            )
        }
    }
}