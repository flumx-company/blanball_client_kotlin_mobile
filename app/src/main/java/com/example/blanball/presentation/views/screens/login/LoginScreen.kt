    package com.example.blanball.presentation.views.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.PrivacyPolicyBanner
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.PassTextInput
import com.example.blanball.utils.ext.isInReqRange
import com.example.blanball.utils.ext.isNotInReqRange
import com.example.blanball.utils.ext.isNotValidEmail
import com.example.blanball.utils.ext.isValidEmail

@Composable
fun LoginScreen(
    state: UiState,
    onLoginClicked: () -> Unit,
    dontRememberButtonClicked: () -> Unit,
    registrationButtonClicked:  () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    val currentState: StartScreensMainContract.State =
        (state as? StartScreensMainContract.State) ?: StartScreensMainContract.State(StartScreensMainContract.ScreenViewState.Idle)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 14.dp
            )
    ) {
        (state as? StartScreensMainContract.State)?.let {
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
            Spacer(modifier = Modifier.padding(24.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_1_one_color),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 104.dp, 118.dp)
                    .align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = stringResource(id = R.string.blanball),
                style = typography.h1,
                color = primaryDark,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(6.dp))
            Text(
                text = stringResource(id = R.string.auth_in_system),
                style = typography.h2,
                color = primaryDark,
                modifier = Modifier.align(CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(32.dp))
            DefaultTextInput(
                modifier = Modifier
                    .fillMaxWidth(),
                labelResId = (R.string.login),
                state = it,
                value = state.loginEmailText.value,
                onValueChange = { state.loginEmailText.value = it },
                transformation = VisualTransformation.None,
                isError = when {
                    it.loginEmailText.value.isNotValidEmail() -> true
                    it.isErrorLoginRequest.value -> true
                    else -> false
                },
                errorMessage = when {
                    it.loginEmailText.value.isNotValidEmail() -> stringResource(id = R.string.format_error_email)
                    it.isErrorLoginRequest.value -> stringResource(id = R.string.invalid_credential_error)
                    else -> {""}
                },
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            )
            Spacer(modifier = Modifier.size(12.dp))
            PassTextInput(
                labelResId = R.string.password,
                state = it,
                isError = when {
                    it.loginPasswordText.value.isNotInReqRange(8) -> true
                    it.isErrorLoginRequest.value -> true
                    else -> false
                },
                errorMessage = when {
                    it.loginPasswordText.value.isNotInReqRange(8) -> stringResource(id = R.string.min_chars_error_pass)
                    it.isErrorLoginRequest.value -> stringResource(id = R.string.invalid_credential_error)
                    else -> {""}
                },
                value = state.loginPasswordText.value,
                onValueChange = { state.loginPasswordText.value = it },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy( imeAction =  ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {localFocusManager.clearFocus()}),
                visibilityIconState = it.loginPasswordVisibility,
            )
            Spacer(modifier = Modifier.size(12.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Checkbox(
                    checked = it.rememberMeCheckbox.value,
                    onCheckedChange = { state.rememberMeCheckbox.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = mainGreen,
                        uncheckedColor = defaultLightGray,
                    ),
                    modifier = Modifier.size(15.dp),
                )
                Spacer(modifier = Modifier.size(10.5.dp))
                Text(
                    modifier = Modifier.clickable { it.rememberMeCheckbox.value = !(it.rememberMeCheckbox.value) } ,
                    text = stringResource(id = R.string.remember_me),
                    style = typography.h6,
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.i_dont_remember),
                    style = typography.h6,
                    color = secondaryNavy,
                    modifier = Modifier.clickable(onClick = dontRememberButtonClicked)
                )
            }
        }
        Spacer(modifier = Modifier.size(25.dp))
        Button(
            enabled = currentState.loginEmailText.value.isValidEmail() && currentState.loginPasswordText.value.isInReqRange(min = 8),
            onClick = onLoginClicked,
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
                text = stringResource(id = R.string.sign_in),
                style = typography.h4,
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Row() {
            Text(
                text = stringResource(id = R.string.dont_have_acc),
                style = typography.h6,
                color = secondaryNavy
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResource(id = R.string.register),
                style = typography.h6,
                color = mainGreen,
                modifier = Modifier.clickable(onClick = registrationButtonClicked)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.size(34.dp))
        PrivacyPolicyBanner()
    }
    if (currentState.state is StartScreensMainContract.ScreenViewState.Loading) {
        Loader()
    }
}