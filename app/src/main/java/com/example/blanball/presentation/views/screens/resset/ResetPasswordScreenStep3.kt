package com.example.blanball.presentation.views.screens.resset

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.animations.AnimationRotatingBalls
import com.example.blanball.presentation.views.components.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.textinputs.PassTextInput
import com.example.blanball.utils.ext.isInReqRange
import com.example.blanball.utils.ext.isNotInReqRange

@Composable
fun ResetPasswordScreenStep3(
    state: UiState,
    onFinishResetClicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {
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
            AnimatedPaddingCard(
            {
                Column(
                    modifier = Modifier.padding(
                        top = 28.dp,
                        start = 16.dp,
                        bottom = 30.dp,
                        end = 16.dp,
                    ).verticalScroll(rememberScrollState()),
                ) {
                    AnimationRotatingBalls()
                    Text(
                        text = stringResource(R.string.resumption_acces),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = primaryDark,
                        textAlign = TextAlign.Center,
                    )
                    Row(
                        Modifier.padding(top = 20.dp)
                    ) {
                        repeat(3) {
                            Image(
                                painter = painterResource(R.drawable.stepline_1),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.size(2.dp))
                        }
                    }
                    Text(
                        text = stringResource(R.string.create_a_new_password),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        style = typography.h3,
                        color = secondaryNavy,
                        textAlign = TextAlign.Start,
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    PassTextInput(
                        labelResId = R.string.new_pass,
                        value = state.newPassText.value,
                        onValueChange = { state.newPassText.value = it },
                        state = it,
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions.Default,
                        isError = when {
                            it.newPassText.value.isNotInReqRange(8) -> true
                            it.newPassText.value != it.repeatNewPassText.value -> true
                            it.isErrorCompleteResetState.value -> true
                            else -> false
                        },
                        visibilityIconState = it.passwordResetVisibility,
                        errorMessage = when {
                            it.newPassText.value.isNotInReqRange(8) -> stringResource(id = R.string.min_chars_error_pass)
                            it.newPassText.value != it.repeatNewPassText.value -> stringResource(id = R.string.doesnt_math_pass)
                            it.isErrorCompleteResetState.value -> stringResource(id = R.string.invalid_credential_error)
                            else -> {
                                ""
                            }
                        },
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    PassTextInput(
                        labelResId = R.string.repeat_new_pass,
                        value = state.repeatNewPassText.value,
                        onValueChange = { state.repeatNewPassText.value = it },
                        state = it,
                        visibilityIconState = it.repeatPasswordResetVisibility,
                        modifier = Modifier
                            .fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions { localFocusManager.clearFocus() },
                        isError = when {
                            it.newPassText.value.isNotInReqRange(8) -> true
                            it.newPassText.value != it.repeatNewPassText.value -> true
                            it.isErrorCompleteResetState.value -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.newPassText.value.isNotInReqRange(8) -> stringResource(id = R.string.min_chars_error_pass)
                            it.newPassText.value != it.repeatNewPassText.value -> stringResource(id = R.string.doesnt_math_pass)
                            it.isErrorCompleteResetState.value -> stringResource(id = R.string.invalid_credential_error)
                            else -> {
                                ""
                            }
                        },
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.size(24.dp))
                        Button(
                            enabled = currentState.newPassText.value.isInReqRange(min = 8) && currentState.repeatNewPassText.value == currentState.newPassText.value,
                            onClick = onFinishResetClicked,
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
                                text = stringResource(id = R.string.send_code),
                                style = typography.h4,
                            )
                        }
                        TextButton(
                            onClick = onCancelClicked,
                            Modifier
                                .padding(top = 14.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = typography.h4,
                            )
                        }
                    }
                },
                enableAnimation = true)
        }
        if (currentState.state is StartScreensMainContract.ScreenViewState.Loading) {
            Loader()
        }
    }
}