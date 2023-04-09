package com.example.blanball.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.*


@Composable
fun ResetPasswordScreen(
    state: UiState,
    onStep2Clicked: () -> Unit,
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
            val mainContainer = Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp),
                shape = shapes.large,
            )
            {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = stringResource(R.string.resumption_acces),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp),
                        textAlign = TextAlign.Start,
                        style = typography.h2,
                    )
                    Row(
                    ) {
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                        Image(
                            painter = painterResource(id = R.drawable.empty_stepline),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
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
                    )

                    CustomisedIT(
                        labelResId = R.string.email,
                        transformation = PasswordVisualTransformation(),
                        state = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.message_atention),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp),
                        textAlign = TextAlign.Start,
                        style = typography.h4,
                    )
                }
            }
        }
    }
}

@Composable
fun CustomisedIT(
    labelResId: Int,
    transformation: VisualTransformation,
    state: MainContract.State,
    modifier: Modifier,
) {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = modifier,
        value = state.emailText,
        onValueChange = { state.emailText = it },
        visualTransformation = transformation,
        label = {
            Text(
                stringResource(
                    id = labelResId
                )
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = textFieldBorder,
            focusedBorderColor = textFieldBorder,
            textColor = Color.Black,
            errorBorderColor = pink200,
            focusedLabelColor = textColor
        )
    )
}