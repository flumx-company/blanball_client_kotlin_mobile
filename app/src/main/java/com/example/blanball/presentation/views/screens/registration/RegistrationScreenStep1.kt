package com.example.blanball.presentation.views.screens.registration

import OutlineRadioButton
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.widgets.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.widgets.loaders.Loader
import com.example.blanball.presentation.views.widgets.textinputs.DefaultTextInput

@Composable
fun RegistrationScreenStep1(
    state: UiState,
    onRegistrationStep2Clicked: () -> Unit,
) {
    val currentState: MainContract.State =
        (state as? MainContract.State) ?: MainContract.State(MainContract.ScreenViewState.Idle)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? MainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.ukraine),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            AnimatedPaddingCard()
            {
                Column(
                    modifier = Modifier.padding(
                        top = 28.dp,
                        start = 16.dp,
                        bottom = 30.dp,
                        end = 16.dp,
                    )
                ) {
                    Text(
                        text = stringResource(R.string.creation_new_acc),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = primaryDark,
                        textAlign = TextAlign.Center,
                    )
                    Row(
                        Modifier
                            .padding(top = 20.dp)
                            .align(CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(
                            painter = painterResource(id = R.drawable.empty_stepline),
                            contentDescription = null,
                        )
                    }
                    DefaultTextInput(
                        labelResId = (R.string.your_name),
                        state = it,
                        value = state.nameText.value,
                        onValueChange = { state.nameText.value = it },
                        transformation = VisualTransformation.None,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                    )
                    DefaultTextInput(
                        labelResId = (R.string.you_phone_number),
                        state = it,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = state.phoneNumberText.value,
                        onValueChange = { state.phoneNumberText.value = it },
                        transformation = VisualTransformation.None,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                    )
                    Row(
                        Modifier.padding(top = 20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.your_gender),
                            style = typography.h5,
                            color = primaryDark
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = stringResource(id = R.string.can_pick_once),
                            style = typography.h6,
                            color = secondaryNavy,
                            modifier = Modifier.background(
                                color = backgroundItems,
                                shape = shapes.small
                            )
                        )
                    }
                    Row(Modifier.padding(top = 20.dp)) {
                        OutlineRadioButton(
                            state = it,
                            text = stringResource(R.string.male),
                            selected = it.genderIsMale.value,
                            icon = painterResource(id = R.drawable.male_ic),
                            onClick = {
                                it.genderIsMale.value = true
                                it.genderIsFemale.value = false
                            }
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        OutlineRadioButton(
                            state = it,
                            text = stringResource(R.string.female),
                            selected = it.genderIsFemale.value,
                            icon = painterResource(id = R.drawable.female_ic),
                            onClick = {
                                it.genderIsFemale.value = true
                                it.genderIsMale.value = false
                            },
                        )
                    }
                }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Bottom,
                    ) {
                        Button(
                            onClick =  onRegistrationStep2Clicked,
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
                            onClick = {},
                            Modifier
                                .padding(top = 14.dp)
                                .align(CenterHorizontally)
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = typography.h4,
                            )
                        }
                    }
                }
                if (currentState.state is MainContract.ScreenViewState.Loading) {
                    Loader()
                }
            }
        }
    }