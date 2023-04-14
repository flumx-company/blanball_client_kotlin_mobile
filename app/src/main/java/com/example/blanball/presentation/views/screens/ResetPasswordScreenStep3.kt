package com.example.blanball.presentation.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.*
import com.example.blanball.presentation.views.widgets.textinputs.PassTextInput

@Composable
fun ResetPasswordScreenStep3(
    state: UiState,
    onFinishResetClicked: () -> Unit,
) {
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
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp),
                shape = shapes.large,
            )
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
                    PassTextInput(
                        labelResId = R.string.new_pass,
                        value = state.newPassText.value,
                        onValueChange = { state.newPassText.value = it },
                        state = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                    PassTextInput(
                        labelResId = R.string.repeat_new_pass,
                        value = state.repeatNewPassText.value,
                        onValueChange = { state.repeatNewPassText.value = it },
                        state = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
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
                        onClick = onFinishResetClicked,
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
                    TextButton(onClick = { /*TODO*/ }, Modifier.padding(top = 14.dp).align(Alignment.CenterHorizontally)) {
                        Text(
                            text = stringResource(id = R.string.cancel),
                            style = typography.h4,
                        )
                    }
                }
            }
        }
    }
}