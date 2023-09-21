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
import androidx.compose.ui.text.input.VisualTransformation
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
import com.example.blanball.presentation.views.components.banners.PrivacyPolicyBanner
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsVertical
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.utils.ext.isNotValidEmail
import com.example.blanball.utils.ext.isValidEmail


@Composable
fun ResetPasswordScreenStep1(
    state: UiState,
    onStep2Clicked: () -> Unit,
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
                Text(
                    text = stringResource(R.string.send_email_text_step_1),
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
                DefaultTextInput(
                    modifier = Modifier.fillMaxWidth(),
                    labelResId = R.string.email,
                    state = it,
                    value = state.resetEmailText.value,
                    onValueChange = { state.resetEmailText.value = it },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() }),
                    transformation = VisualTransformation.None,
                    isError = when {
                        it.resetEmailText.value.isNotValidEmail() -> true
                        it.isErrorResetEmailState.value -> true
                        else -> false
                    },
                    errorMessage = when {
                        it.resetEmailText.value.isNotValidEmail() -> stringResource(id = R.string.format_error_email)
                        it.isErrorResetEmailState.value -> stringResource(id = R.string.invalid_credential_error)
                        else -> {
                            ""
                        }
                    }
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(24.dp))
                NextAndPreviousButtonsVertical(
                    isEnabled = currentState.resetEmailText.value.isValidEmail(),
                    nextBtnOnClick = onStep2Clicked,
                    prevBtnOnClick = onCancelClicked,
                    nextBtnOnTextId = R.string.send_code,
                    prevBtnOnTextId = R.string.cancel,
                )
                Spacer(modifier = Modifier.size(20.dp))
                PrivacyPolicyBanner()
            }
        }
    }
    if (currentState.state is StartScreensMainContract.ScreenViewState.Loading) {
        Loader()
    }
}