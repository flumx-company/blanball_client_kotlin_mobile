package com.example.blanball.presentation.views.screens.onboarding

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.OnboardingScreensStatesMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.LightGray
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.loaders.Loader

@Composable
fun FillingOutTheUserProfileScreenStep4(
    state: UiState,
    onFinishClicked: () -> Unit,
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
                painter = painterResource(id = R.drawable.ukraine),
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
                        text = stringResource(R.string.locations),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = primaryDark,
                        textAlign = TextAlign.Center,
                    )
                    Row(
                        Modifier.padding(top = 20.dp)
                    ) {
                        repeat(4) {
                            Image(
                                painter = painterResource(R.drawable.stepline_1),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.size(2.dp))
                        }
                    }
                    Spacer(modifier = Modifier.size(24.dp))
                    Text(
                        text = stringResource(id = R.string.help_us_figure_out),
                        style = typography.h3,
                        color = secondaryNavy
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        text = stringResource(id = R.string.your_place_of_residence),
                        style = typography.h5,
                        color = primaryDark
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    CustomDropDownMenu(
                        labelResId = R.string.city,
                        listItems = listOf(),
                        value = it.cityState.value,
                        onValueChange = { state.cityState.value = it }
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    CustomDropDownMenu(
                        labelResId = R.string.area,
                        listItems = listOf(),
                        value = it.districtState.value,
                        onValueChange = { state.districtState.value = it }
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Box(
                        modifier = Modifier,
                        contentAlignment = Alignment.TopEnd
                    ) {
                        CustomDropDownMenu(
                            labelResId = R.string.add_district,
                            listItems = listOf(),
                            value = it.addDistrictState.value,
                            onValueChange = { state.addDistrictState.value = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = stringResource(id = R.string.optional),
                            style = typography.h6,
                            color = LightGray,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .background(
                                    color = backgroundItems,
                                    shape = shapes.small
                                )
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        onClick = onFinishClicked,
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
    if (currentState.state is OnboardingScreensStatesMainContract.ScreenViewState.Loading) {
        Loader()
    }
}