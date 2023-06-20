package com.example.blanball.presentation.views.screens.onboarding

import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu

@Composable
fun FillingOutTheUserProfileScreenStep1(
    state: UiState,
    onFillingOutTheUserProfileStep2Clicked: () -> Unit,
    onTurnBackClicked: () -> Unit,
    years: List<String>
) {
    val dayNumbers = remember { (1..30).toList() }
    val months = listOf(
        stringResource(R.string.january),
        stringResource(R.string.february),
        stringResource(R.string.march),
        stringResource(R.string.april),
        stringResource(R.string.may),
        stringResource(R.string.june),
        stringResource(R.string.july),
        stringResource(R.string.august),
        stringResource(R.string.september),
        stringResource(R.string.october),
        stringResource(R.string.november),
        stringResource(R.string.december)
    )
    val rememberMonth = remember {
        months
    }

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
                painter = painterResource(id = R.drawable.onboard_bg),
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
                        repeat(2) {
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
                        CustomDropDownMenu(
                            labelResId = R.string.day,
                            listItems = dayNumbers.map { it.toString() },
                            value = it.dayDropDownState.value,
                            onValueChange = { state.dayDropDownState.value = it },
                            modifier = Modifier.weight(1f)
                        )
                        CustomDropDownMenu(
                            labelResId = R.string.month,
                            listItems = rememberMonth,
                            value = it.monthDropDownState.value,
                            onValueChange = { state.monthDropDownState.value = it },
                            modifier = Modifier.weight(1f)
                        )
                        CustomDropDownMenu(
                            labelResId = R.string.year,
                            listItems = years,
                            value = it.yearDropDownState.value,
                            onValueChange = { state.yearDropDownState.value = it },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        onClick = onFillingOutTheUserProfileStep2Clicked,
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