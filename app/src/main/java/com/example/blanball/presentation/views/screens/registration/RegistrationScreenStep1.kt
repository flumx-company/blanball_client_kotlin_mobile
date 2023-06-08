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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.PhoneNumberInput
import com.example.blanball.utils.ext.isInvalidValidPhoneNumber
import com.example.blanball.utils.ext.isNotValidUserName
import com.example.blanball.utils.ext.isValidPhoneNumber
import com.example.blanball.utils.ext.isValidUserName

@Composable
fun RegistrationScreenStep1(
    state: UiState,
    onRegistrationStep2Clicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
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
                    Spacer(modifier = Modifier.size(20.dp))
                    DefaultTextInput(
                        labelResId = (R.string.your_firstname),
                        state = it,
                        value = state.firstNameText.value,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = { state.firstNameText.value = it },
                        isError = when {
                            it.isErrorRegistrationNewPass.value -> true
                            it.firstNameText.value.isNotValidUserName() -> true
                            else -> false },
                        errorMessage = when {
                            it.isErrorRegistrationNewPass.value -> stringResource(id = R.string.invalid_credential_error)
                            it.firstNameText.value.isNotValidUserName() -> stringResource(id = R.string.letter_only_error)
                            else -> {("")} },
                        transformation = VisualTransformation.None,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    DefaultTextInput(
                        labelResId = (R.string.your_lastname),
                        state = it,
                        value = state.lastNameText.value,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        onValueChange = { state.lastNameText.value = it },
                        isError = when {
                            it.isErrorRegistrationNewPass.value -> true
                            it.lastNameText.value.isNotValidUserName() -> true
                            else -> false },
                        errorMessage = when {
                            it.isErrorRegistrationNewPass.value -> stringResource(id = R.string.invalid_credential_error)
                            it.lastNameText.value.isNotValidUserName() -> stringResource(id = R.string.letter_only_error)
                            else -> {("")} },
                        transformation = VisualTransformation.None,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    PhoneNumberInput(
                        value = state.phoneNumberText.value,
                        onValueChange =  { state.phoneNumberText.value = it },
                        modifier = Modifier
                            .fillMaxWidth(),
                        isError = when {
                            it.phoneNumberText.value.isInvalidValidPhoneNumber() -> true
                            it.isErrorRegistrationNewPass.value -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.phoneNumberText.value.isInvalidValidPhoneNumber() -> stringResource(
                                id = R.string.phone_format_error
                            )
                            it.isErrorRegistrationNewPass.value -> stringResource(id = R.string.invalid_credential_error)
                            else -> {("")}
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {localFocusManager.clearFocus()})
                    )
                    Spacer(Modifier.size(12.dp))
                    Row (verticalAlignment = CenterVertically) {
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
                            modifier = Modifier
                                .background(
                                    color = backgroundItems, shape = shapes.small
                                )
                                .padding(start = 2.dp, end = 2.dp))
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
                                && state.firstNameText.value.isValidUserName()
                                && state.lastNameText.value.isValidUserName()
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
                        onClick = onCancelClicked,
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