package com.example.blanball.presentation.views.screens.foundanerror

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.FoundAnErrorScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.darkPurple
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.successGreen
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.utils.ext.isNotValidErrorTopicField
import com.example.blanball.utils.ext.isValidErrorTopicField

@Composable
fun FoundAnErrorScreen(
    state: UiState,
    paddingValues: PaddingValues
//    sendFeedbackButtonClicked: () -> Unit,
//    closeButtonClicked: () -> Unit, TODO()
) {
    Box (
        modifier = Modifier.padding(paddingValues)
    ) {
        (state as? FoundAnErrorScreenMainContract.State)?.let {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp, end = 20.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.report_an_error_in_the_app),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(600),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error),
                        contentDescription = null,
                        tint = primaryDark
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                DefaultTextInput(
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_target),
                            contentDescription = null,
                            tint = secondaryNavy
                        )
                    },
                    labelResId = R.string.what_is_the_error_related_to,
                    state = it,
                    value = state.errorTopicText.value,
                    onValueChange = { state.errorTopicText.value = it },
                    transformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    isError = when {
                        it.errorTopicText.value.isNotValidErrorTopicField() -> true
                        else -> false
                    }, // TODO()
                    errorMessage = when {
                        it.errorTopicText.value.isNotValidErrorTopicField() -> stringResource(R.string.validation_text_error_topic)
                        else -> {
                            ("")
                        }
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = { Icon(
                        modifier = Modifier.clickable { }, //TODO()
                        painter = painterResource(R.drawable.ic_clip),
                        contentDescription = null,
                        tint = secondaryNavy
                    )
                    },
                    textFieldModifier = Modifier
                        .fillMaxWidth()
                        .height(104.dp),
                    state = it,
                    isSingleLine = false,
                    value = state.errorDescriptionText.value,
                    onValueChange = { state.errorDescriptionText.value = it },
                    transformation = VisualTransformation.None,
                    labelResId = R.string.when_and_where_did_you_encounter_the_error,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                )
                Spacer(modifier = Modifier.size(18.dp))
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_document_sending),
                        contentDescription = null,
                        tint = secondaryNavy,
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = "nazvafail.0187985620.png", //TODO()
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.clickable {  }, // TODO()
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = secondaryNavy,
                    )
                }
                Spacer(modifier = Modifier.size(6.dp))
                Divider(
                    modifier = Modifier.width(212.dp),
                    color = successGreen,
                    thickness = 2.dp,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        modifier = Modifier.clickable { }, //TODO()
                        text = stringResource(R.string.close),
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        enabled = it.errorTopicText.value.isValidErrorTopicField()
                                && it.errorTopicText.value.isNotEmpty(),
                        onClick = { /*TODO*/ },
                        shape = shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = darkPurple,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(
                            text = stringResource(R.string.send_feedback),
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(500),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}