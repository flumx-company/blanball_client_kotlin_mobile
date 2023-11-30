package com.example.blanball.presentation.views.components.modals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EmailVerificationMainContract
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.viewmodels.EmailVerificationViewModel
import com.example.blanball.presentation.views.components.textinputs.CodeTextInput
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.ReadOnlyOutlinePlaceholder
import com.example.blanball.utils.ext.isNotValidCode
import com.example.blanball.utils.ext.isNotValidEmail
import com.example.blanball.utils.ext.isValidCode
import com.example.blanball.utils.ext.isValidEmail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun EmailVerificationModal(
    state: UiState,
    resendCodeToEmailClicked: () -> Unit,
    turnBackBtnClicked: () -> Unit,
    confirmBtnClicked: () -> Unit,
) {
    val initialTimerValue = 30
    var (timerValue, setTimerValue) = remember { mutableStateOf(initialTimerValue) }
    var isRunning by remember { mutableStateOf(false) }
    LaunchedEffect(isRunning) {
        withContext(Dispatchers.IO) {
            while (timerValue >= 0) {
                delay(1000)
                setTimerValue(timerValue--)
            }
            isRunning = false
        }
    }
    val showButton = timerValue == 0
    val configuration = LocalConfiguration.current

    (state as? EmailVerificationMainContract.State)?.let {
        AlertDialog(modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 20.dp)
            .wrapContentHeight(),
            onDismissRequest = { /*TODO*/ }, buttons = {}, text = {

                Column {
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = stringResource(R.string.confiming_email_adress),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    ReadOnlyOutlinePlaceholder(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize(),
                        labelResId = R.string.email,
                        value = state.userEmailText.value,
                        onValueChange = { state.userEmailText.value = it },
                    )
                    if (!showButton)
                        Text(
                            text = "${stringResource(id = R.string.send_mail_repeat)} $timerValue ${
                                stringResource(
                                    id = R.string.sec
                                )
                            }",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            textAlign = TextAlign.End,
                            style = typography.h6,
                            color = secondaryNavy,
                        )
                    if (showButton) {
                        TextButton(
                            modifier = Modifier
                                .align(Alignment.End),
                            onClick = {
                                resendCodeToEmailClicked()
                                setTimerValue(initialTimerValue)
                                isRunning = true
                            },
                        ) {
                            Text(
                                text = stringResource(id = R.string.resend),
                                style = typography.h6,
                                textAlign = TextAlign.Center,
                                color = mainGreen,
                            )
                        }
                    }
                    CodeTextInput(
                        state = it,
                        modifier = Modifier.padding(top = 8.dp),
                        enabled = true,
                        codeText = { it.codeText },
                        onCodeChange = { state, index, newValue ->
                            state.codeText[index].value = newValue.take(1).uppercase()
                        },
                        isError = when {
                            it.codeText.joinToString(separator = "") { it.value }
                                .isNotValidCode() -> true

                            it.isSendCodeToUserEmailError.value-> true
                            else -> false
                        },
                        errorMessage = when {
                            it.isSendCodeToUserEmailError.value -> stringResource(id = R.string.check_code)
                            it.codeText.joinToString(separator = "") { it.value }
                                .isNotValidCode() -> stringResource(
                                id = R.string.letter_only_error
                            )

                            else -> {
                                ""
                            }
                        }
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(onClick = { turnBackBtnClicked() }) {
                            Text(
                                text = stringResource(id = R.string.close),
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(500),
                                color = secondaryNavy,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(
                            enabled = it.codeText.joinToString(separator = "") { it.value }
                                .isValidCode(),
                            onClick = { confirmBtnClicked() },
                            shape = shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = mainGreen,
                                contentColor = Color.White,
                            )
                        ) {
                            Text(
                                text = stringResource(id = R.string.confirm),
                                fontSize = 14.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                }
            }
        )
    }
}