package com.example.blanball.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.pink200
import com.example.blanball.presentation.theme.textColor
import com.example.blanball.presentation.theme.textFieldBorder

@Preview
@Composable
fun ResetPasswordScreen(
    state: UiState,
    onStep2Clicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        (state as? MainContract.State)?.let {
            val defModifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp)
            Spacer(modifier = Modifier.padding(36.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_2_one_color),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(id = R.string.blanball), fontSize = 36.sp)
            Spacer(modifier = Modifier.padding(6.dp))
            Text(text = stringResource(id = R.string.auth_in_system), fontSize = 23.sp)
            Spacer(modifier = Modifier.padding(16.dp))
            CustomisedIT(R.string.login, VisualTransformation.None, it, defModifier)
            Spacer(modifier = Modifier.padding(6.dp))
            CustomisedIT(R.string.password, PasswordVisualTransformation(), it, defModifier)
            Spacer(modifier = Modifier.padding(16.dp))
            Button(
                modifier = defModifier,
                onClick = onStep2Clicked,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = mainGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
}

@Composable
fun CustomisedIT(
    labelResid: Int,
    transformation: VisualTransformation,
    state: MainContract.State,
    modifier: Modifier
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
                    id = labelResid
                )
            )
        },
        shape = RoundedCornerShape(12.dp),
        colors =  TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = textFieldBorder,
            focusedBorderColor = textFieldBorder,
            textColor = Color.Black,
            errorBorderColor = pink200,
            focusedLabelColor = textColor
        )
    )
}