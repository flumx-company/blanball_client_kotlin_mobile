package com.example.blanball.presentation.views.widgets.textinputs

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.example.blanball.presentation.data.MainContract
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.utils.di.validation.EmailForm
import me.naingaungluu.formconductor.FieldResult
import me.naingaungluu.formconductor.composeui.field
import me.naingaungluu.formconductor.composeui.form


@Composable
fun EmailTextInput(
    labelResId: Int,
    transformation: VisualTransformation,
    mainState: MainContract.State,
    modifier: Modifier,
) {
    //TODO: Email form with validation library
    form(className = EmailForm::class) {
        field(fieldClass = EmailForm::email) {
            mainState.emailText.value = state.value?.value.orEmpty()
            mainState.successValidEmail.value = resultState.value is FieldResult.Success
            OutlinedTextField(
                modifier = modifier,
                value = state.value?.value.orEmpty(),
                onValueChange = this::setField,
                isError = resultState.value is FieldResult.Error,
                visualTransformation = transformation,
                singleLine = true,
                label = {
                    Text(
                        stringResource(
                            id = labelResId
                        ),
                    )
                },
                shape = shapes.small,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = defaultLightGray,
                    focusedBorderColor = selectedDarkGray,
                    textColor = Color.Black,
                    errorBorderColor = errorRed,
                    focusedLabelColor = primaryDark,
                    cursorColor = mainGreen,
                )
            )
        }
    }
}