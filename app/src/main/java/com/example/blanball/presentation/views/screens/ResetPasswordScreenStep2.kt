package com.example.blanball.presentation.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.*
import com.example.blanball.presentation.views.widgets.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.widgets.loaders.Loader
import com.example.blanball.presentation.views.widgets.textinputs.CodeTextInput
import com.example.blanball.presentation.views.widgets.textinputs.EmailTextInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun ResetPasswordScreenStep2(
    state: UiState,
    onStep3Clicked: () -> Unit,
    resendCodeToEmailClicked: () -> Unit,
) {
    val currentState: MainContract.State =
        (state as? MainContract.State) ?: MainContract.State(MainContract.ScreenViewState.Idle)
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? MainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.ukraine), contentDescription = null,
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
                        text = stringResource(R.string.resumption_acces),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = primaryDark,
                    )
                    Row(
                        Modifier.padding(top = 20.dp)
                    ) {
                        repeat(2) {
                            Image(
                                painter = painterResource(R.drawable.stepline_1),
                                contentDescription = null,
                                Modifier.weight(1f)
                            )
                            Spacer(modifier = Modifier.size(2.dp))
                        }
                        Image(
                            painter = painterResource(id = R.drawable.empty_stepline),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                    }
                    Text(
                        text = stringResource(R.string.send_email_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        style = typography.h3,
                        color = secondaryNavy,
                        textAlign = TextAlign.Start,
                    )
                    EmailTextInput(
                        labelResId = R.string.email,
                        mainState = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        transformation = VisualTransformation.None
                    )
                    if (!showButton)
                        Text(
                            text = "Надіслати код повторно: $timerValue сек",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 12.dp),
                            textAlign = TextAlign.End,
                            style = typography.h5,
                            color = secondaryNavy,
                        )
                    if (showButton) {
                        TextButton(
                            onClick = {
                                resendCodeToEmailClicked()
                                setTimerValue(initialTimerValue)
                                isRunning = true
                            },
                            contentPadding = PaddingValues(0.dp),
                            modifier = Modifier
                                .align(End),
                        ) {
                            Text(
                                text = stringResource(id = R.string.resend),
                                style = typography.h5,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }
                    CodeTextInput(
                        state = it,
                        modifier = Modifier.padding(top = 8.dp),
                        enabled = true,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom,
                )
                {
                    Button(
                        onClick = onStep3Clicked,
                        Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        shape = shapes.small,
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
                        onClick = { /*TODO*/ },
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
        }
        if (currentState.state is MainContract.ScreenViewState.Loading) {
            Loader()
        }
    }
}