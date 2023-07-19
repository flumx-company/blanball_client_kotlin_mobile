package com.example.blanball.presentation.views.screens.resset

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.animations.AnimationRotatingBalls
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
                        top = 0.dp,
                        start = 16.dp,
                        bottom = 30.dp,
                        end = 16.dp,
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
                Button(
                    enabled = currentState.resetEmailText.value.isValidEmail(),
                    onClick = onStep2Clicked,
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
        }
    }
    if (currentState.state is StartScreensMainContract.ScreenViewState.Loading) {
        Loader()
    }
}