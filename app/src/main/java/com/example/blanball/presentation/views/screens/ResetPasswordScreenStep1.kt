package com.example.blanball.presentation.views.screens

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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import com.example.blanball.presentation.theme.backgroundGradient
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.widgets.cards.AnimatedPaddingCard
import com.example.blanball.presentation.views.widgets.loaders.Loader
import com.example.blanball.presentation.views.widgets.textinputs.CodeTextInput
import com.example.blanball.presentation.views.widgets.textinputs.EmailTextInput


@Composable
fun ResetPasswordScreenStep1(
    state: UiState,
    onStep2Clicked: () -> Unit,
    onCancelClicked: () -> Unit,
) {
    val currentState: MainContract.State = (state as? MainContract.State) ?: MainContract.State(MainContract.ScreenViewState.Idle)
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
                        text = stringResource(R.string.resumption_acces),
                        modifier = Modifier.fillMaxWidth(),
                        style = typography.h2,
                        color = primaryDark,
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
                        transformation = VisualTransformation.None,
                    )
                    Text(
                        text = stringResource(id = R.string.message_atention),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        textAlign = TextAlign.End,
                        style = typography.h5,
                        color = secondaryNavy,
                    )
                    CodeTextInput(
                        state = it, modifier = Modifier.padding(top = 8.dp), enabled = false
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Bottom,
                ) {
                            Button(
                                enabled = it.successValidEmail.value,
                                onClick = onStep2Clicked,
                                modifier = Modifier
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
                            onClick =  onCancelClicked ,
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
        }
        if (currentState.state is MainContract.ScreenViewState.Loading) {
            Loader()
        }
    }