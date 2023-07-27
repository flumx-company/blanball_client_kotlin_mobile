package com.example.blanball.presentation.views.screens.resset

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.animations.AnimationRotatingBalls
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtons
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
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? StartScreensMainContract.State)?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 14.dp
                    )
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimationRotatingBalls()
                Text(
                    text = stringResource(id = R.string.blanball),
                    style = typography.h3,
                    fontSize = 36.sp,
                    lineHeight = 40.sp,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
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
                    fontWeight = FontWeight(400),
                    lineHeight = 24.sp,
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
                        it.repeatNewPassText.value.isNotInReqRange(8) -> stringResource(id = R.string.min_chars_error_pass)
                        it.newPassText.value != it.repeatNewPassText.value -> stringResource(id = R.string.doesnt_math_pass)
                        it.isErrorCompleteResetState.value -> stringResource(id = R.string.invalid_credential_error)
                        else -> {
                            ""
                        }
                    },
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(24.dp))
                NextAndPreviousButtons(
                    isEnabled = currentState.newPassText.value.isInReqRange(min = 8) && currentState.repeatNewPassText.value == currentState.newPassText.value,
                    nextBtnOnClick = onFinishResetClicked,
                    prevBtnOnClick = onCancelClicked,
                    nextBtnOnTextId = R.string.save_new_pass,
                    prevBtnOnTextId = R.string.cancel,
                )
            }
        }
    }
        if (currentState.state is StartScreensMainContract.ScreenViewState.Loading) {
            Loader()
    }
}