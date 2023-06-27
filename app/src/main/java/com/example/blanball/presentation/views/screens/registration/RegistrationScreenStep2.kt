package com.example.blanball.presentation.views.screens.registration

import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.animations.AnimationRotatingBalls
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.switches.SwitchButton
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.PassTextInput
import com.example.blanball.utils.ext.isInReqRange
import com.example.blanball.utils.ext.isNotInReqRange
import com.example.blanball.utils.ext.isNotValidEmail
import com.example.blanball.utils.ext.isValidEmail
import com.example.domain.utils.Endpoints


@Composable
fun RegistrationScreenStep2(
    state: UiState,
    onRegistrationClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val intent =
        Intent(Intent.ACTION_VIEW, Uri.parse(Endpoints.PRIVACY_POLICY_URL))
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    val currentState: StartScreensMainContract.State =
        (state as? StartScreensMainContract.State) ?: StartScreensMainContract.State(
            StartScreensMainContract.ScreenViewState.Idle
        )
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
            AnimatedPaddingCard() {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            top = 28.dp,
                            start = 16.dp,
                            bottom = 30.dp,
                            end = 16.dp,
                        )
                ) {
                    AnimationRotatingBalls()
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
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(
                            painter = painterResource(id = R.drawable.stepline_1),
                            contentDescription = null,
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                        DefaultTextInput(
                            labelResId = (R.string.email),
                            state = state,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Next
                            ),
                            transformation = VisualTransformation.None,
                            value = state.registrationEmailText.value,
                            onValueChange = { state.registrationEmailText.value = it },
                            isError = when {
                                it.registrationEmailText.value.isNotValidEmail() -> true
                                it.isErrorRegistrationNewPass.value -> true
                                else -> false
                            },
                            errorMessage = when {
                                it.registrationEmailText.value.isNotValidEmail() -> stringResource(
                                    id = R.string.format_error_email
                                )

                                it.isErrorRegistrationNewPass.value -> stringResource(id = R.string.invalid_credential_error)
                                else -> {
                                    ""
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    Spacer(modifier = Modifier.size(12.dp))
                    PassTextInput(
                        labelResId = R.string.create_password,
                        value = it.registrationPassText.value,
                        onValueChange = { state.registrationPassText.value = it },
                        state = it,
                        isError = when {
                            it.registrationPassTextRemember.value.isNotInReqRange(8) -> true
                            it.registrationPassText.value != it.registrationPassTextRemember.value -> true
                            it.isErrorRegistrationNewPass.value -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.registrationPassTextRemember.value.isNotInReqRange(8) -> stringResource(
                                id = R.string.min_chars_error_pass
                            )

                            it.registrationPassText.value != it.registrationPassTextRemember.value -> stringResource(
                                id = R.string.doesnt_math_pass
                            )

                            it.isErrorRegistrationNewPass.value -> stringResource(id = R.string.invalid_credential_error)
                            else -> {
                                ""
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions.Default,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    PassTextInput(
                        labelResId = R.string.repeat_password,
                        value = it.registrationPassTextRemember.value,
                        onValueChange = { state.registrationPassTextRemember.value = it },
                        isError = when {
                            it.registrationPassTextRemember.value.isNotInReqRange(8) -> true
                            it.registrationPassText.value != it.registrationPassTextRemember.value -> true
                            it.isErrorRegistrationNewPass.value -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.registrationPassTextRemember.value.isNotInReqRange(8) -> stringResource(
                                id = R.string.min_chars_error_pass
                            )

                            it.registrationPassText.value != it.registrationPassTextRemember.value -> stringResource(
                                id = R.string.doesnt_math_pass
                            )

                            it.isErrorRegistrationNewPass.value -> stringResource(id = R.string.invalid_credential_error)
                            else -> {
                                ""
                            }
                        },
                        state = it,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() }),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Row(modifier = Modifier.padding(top = 32.dp)) {
                        SwitchButton(
                            state = it,
                            selected = it.lostInSystemSwitchButton.value,
                            onCheckedChange = { state.lostInSystemSwitchButton.value = it })
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = stringResource(id = R.string.lost_in_system),
                            style = typography.h6,
                            color = primaryDark
                        )
                    }
                    Row(Modifier.padding(top = 16.5.dp)) {
                        Checkbox(
                            checked = it.privacyPolicyCheckbox.value,
                            onCheckedChange = { state.privacyPolicyCheckbox.value = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = mainGreen,
                                uncheckedColor = defaultLightGray,
                            ),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.size(10.5.dp))
                        Text(
                            text = stringResource(id = R.string.accept_terms).plus(" "),
                            style = typography.h6,
                            color = primaryDark,
                            textAlign = TextAlign.Start
                        )
                        Text(
                            text = stringResource(R.string.privacy_policy),
                            textDecoration = TextDecoration.Underline,
                            style = typography.h6,
                            color = primaryDark,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.clickable {
                                startActivity(context, intent, null)
                            }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                    Button(
                        enabled = it.registrationPassText.value.isInReqRange(8)
                                && it.registrationPassTextRemember.value.isInReqRange(8)
                                && it.registrationPassText.value == it.registrationPassTextRemember.value
                                && it.registrationEmailText.value.isValidEmail()
                                && state.privacyPolicyCheckbox.value,
                        onClick = onRegistrationClicked,
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
                            text = stringResource(id = R.string.register),
                            style = typography.h4,
                        )
                    }
                    TextButton(
                        onClick = onBackClicked,
                        Modifier
                            .padding(top = 14.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(id = R.string.back),
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