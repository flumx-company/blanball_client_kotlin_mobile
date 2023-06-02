package com.example.blanball.presentation.views.screens.registration

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
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
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.*
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.utils.ext.isInReqRange
import com.example.blanball.utils.ext.isInvalidValidPhoneNumber
import com.example.blanball.utils.ext.isNotInReqRange
import com.example.blanball.utils.ext.isValidPhoneNumber
import androidx.compose.material.OutlinedTextField

@Composable
fun RegistrationScreenStep1(
    state: UiState,
    onRegistrationStep2Clicked: () -> Unit,
) {
    val currentState: StartScreensMainContract.State =
        (state as? StartScreensMainContract.State) ?: StartScreensMainContract.State(StartScreensMainContract.ScreenViewState.Idle)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? StartScreensMainContract.State)?.let {
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
                        isError = state.nameText.value.isNotInReqRange(3),
                        transformation = VisualTransformation.None,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                    )

                    //TODO Create some new field for text with prefix!
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(),
                        value = state.phoneNumberText.value,
                        onValueChange = { state.phoneNumberText.value = it },
                        singleLine = true,
                        label = {
                            Text(
                                stringResource(
                                    id = R.string.you_phone_number
                                ),
                            )
                        },
                        shape = shapes.small,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = defaultLightGray,
                            focusedBorderColor = selectedDarkGray,
                            textColor = Color.Black,
                            errorBorderColor = errorRed,
                            focusedLabelColor = primaryDark,
                            cursorColor = mainGreen,
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = it.phoneNumberText.value.isInvalidValidPhoneNumber(),
                        leadingIcon = {
                            Text(
                                text = "+380",//TODO Make some resource
                                modifier = Modifier.padding(start = 14.dp),
                                color = Color.Black
                            )
                        }
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
                                color = backgroundItems, shape = shapes.small
                            )
                        )
                    }
                    Row(Modifier.padding(top = 20.dp)) {
                        OutlineRadioButton(state = it,
                            text = stringResource(R.string.male),
                            selected = it.genderIsMale.value,
                            icon = painterResource(id = R.drawable.male_ic),
                            onClick = {
                                it.genderIsMale.value = true
                                it.genderIsFemale.value = false
                            })
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
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        enabled = it.phoneNumberText.value.isValidPhoneNumber()
                                && state.nameText.value.isInReqRange(3)
                                && (state.genderIsMale.value || state.genderIsFemale.value),
                        onClick = onRegistrationStep2Clicked,
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
            if (currentState.state is StartScreensMainContract.ScreenViewState.Loading) {
                Loader()
            }
        }
    }
}