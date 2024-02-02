package com.example.blanball.presentation.views.screens.onboarding.fillingouttheprofile

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsVertical
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.loaders.Loader

@Composable
fun FillingOutTheUserProfileScreenStep4(
    state: UiState,
    onFinishClicked: () -> Unit,
    onTurnBackClicked: () -> Unit,
    onUpdateCitiesForRegionList: () -> Unit,
) {
    val currentState: OnboardingScreensStatesMainContract.State =
        (state as? OnboardingScreensStatesMainContract.State)
            ?: OnboardingScreensStatesMainContract.State(
                OnboardingScreensStatesMainContract.ScreenViewState.Idle
            )
    val localFocusManager = LocalFocusManager.current
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
                        text = stringResource(R.string.locations),
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
                        repeat(4) {
                            Image(
                                painter = painterResource(R.drawable.stepline_1),
                                contentDescription = null,
                                Modifier
                                    .weight(1f)
                                    .height(4.dp),
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
                        color = primaryDark,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(700),
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    CustomDropDownMenu(
                        labelResId = R.string.region,
                        listItems = it.regionOfUkraineList.value,
                        value = it.selectedRegion.value,
                        onValueChange = {
                            state.selectedRegion.value = it
                            onUpdateCitiesForRegionList()
                        }
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    CustomDropDownMenu(
                        labelResId = R.string.city,
                        listItems = it.citiesForRegionList.value,
                        value = it.selectedCity.value,
                        onValueChange = { state.selectedCity.value = it }
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    NextAndPreviousButtonsVertical(
                        isEnabled = it.selectedCity.value.isNotEmpty()
                                && it.selectedRegion.value.isNotEmpty(),
                        nextBtnOnClick = onFinishClicked,
                        prevBtnOnClick = onTurnBackClicked,
                        nextBtnOnTextId = R.string.finish,
                        prevBtnOnTextId = R.string.turn_back,
                    )
                }
            },
                enableAnimation = false
            )
        }
    }
    if (currentState.state is OnboardingScreensStatesMainContract.ScreenViewState.Loading) {
        Loader()
    }
}