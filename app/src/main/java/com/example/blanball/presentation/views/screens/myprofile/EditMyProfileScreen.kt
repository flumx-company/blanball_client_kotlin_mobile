package com.example.blanball.presentation.views.screens.myprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.lightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.cards.MyRatingCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.switches.SwitchButton
import com.example.blanball.presentation.views.components.tabrows.TabRow
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.texts.MyProfileMainGreenTextBadge
import com.example.blanball.utils.ext.isNotValidHeight
import com.example.blanball.utils.ext.isNotValidWeight

@Composable
fun EditMyProfileScreen(
    state: UiState,
    paddingValues: PaddingValues,
    cancelBtnClicked: () -> Unit,
    saveBtnClicked: () -> Unit,
) {
    val icons: List<Painter> = listOf(
        painterResource(id = R.drawable.ic_user),
        painterResource(id = R.drawable.ic_rate)
    )

    val tabs: List<String> = listOf(
        stringResource(id = R.string.my_profile),
        stringResource(R.string.rate_plan),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        (state as? MyProfileScreensMainContract.State)?.let {
            Column (modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp))
            {
                Text(
                    text = stringResource(R.string.my_cabinet).uppercase(),
                    fontSize = 24.sp,
                    lineHeight = 32.sp,
                    style = typography.h2,
                    fontWeight = FontWeight(800),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Оновіть своє фото та персональні дані",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                TabRow(tabs = tabs, icons = icons)
                Spacer(modifier = Modifier.size(20.dp))
                DefaultCardWithColumn {
                    Row {
                        if (state.myAvatarUrl.value.isNullOrEmpty()) {
                            Box(
                                modifier = Modifier.size(56.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.circle_avatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(4.dp))
                                        .fillMaxSize()
                                )
                                Text(
                                    text = "${state.myLastNameText.value.firstOrNull() ?: ""} ${state.myFirstNameText.value.firstOrNull() ?: ""}",
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    ),
                                    style = typography.h2, fontSize = 56.sp, color = mainGreen
                                )
                            }
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(state.myAvatarUrl.value),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Column {
                            Text(
                                text = "Стефанія Калиновська", //TODO()
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(700),
                                color = primaryDark,
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Row {
                                MyProfileMainGreenTextBadge(text = "Гравець")
                                Spacer(modifier = Modifier.size(4.dp))
                                MyProfileMainGreenTextBadge(text = "25 років")
                                Spacer(modifier = Modifier.size(4.dp))
                                MyProfileMainGreenTextBadge(text = "Жінка")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                MyRatingCard()
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.about_me),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.your_lastname,
                    state = it ,
                    value = it.myLastNameText.value,
                    onValueChange = { state.myLastNameText.value} ,
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId = R.string.your_firstname ,
                    state = it,
                    value = it.myFirstNameText.value,
                    onValueChange = { state.myFirstNameText.value = it } ,
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId =R.string.a_few_words_about_me,
                    state = it ,
                    value = it.aboutMeText.value,
                    onValueChange = {state.aboutMeText.value = it} ,
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(id = R.string.game_stats),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DefaultTextInput(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth(),
                        state = it,
                        labelResId = R.string.height,
                        value = it.heightState.value,
                        onValueChange = { state.heightState.value = it },
                        transformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next, keyboardType = KeyboardType.Number),
                        isError = when {
                            it.isErrorRequestToFinishOutTheProfile.value -> true
                            it.heightState.value.isNotValidHeight() ->  true
                            else -> false
                        },
                        errorMessage = when {
                            it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                            it.heightState.value.isNotValidHeight() ->  stringResource(id = R.string.height_valid_error)
                            else -> {("")}
                        }
                    )
                    DefaultTextInput(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth(),
                        state = it,
                        labelResId = R.string.weight,
                        value = it.weightState.value,
                        onValueChange = { state.weightState.value = it },
                        transformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy( imeAction =  ImeAction.Done, keyboardType = KeyboardType.Number),
                        keyboardActions = KeyboardActions(onDone = {localFocusManager.clearFocus()}),
                        isError = when {
                            it.isErrorRequestToFinishOutTheProfile.value -> true
                            it.weightState.value.isNotValidWeight() ->  true
                            else -> false
                        },
                        errorMessage = when {
                            it.isErrorRequestToFinishOutTheProfile.value -> stringResource(id = R.string.invalid_credential_error)
                            it.weightState.value.isNotValidWeight() ->  stringResource(id = R.string.weight_valid_error)
                            else -> {("")}
                        }
                    )
                    CustomDropDownMenu(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        labelResId = R.string.kicking_leg,
                        listItems = listOf(
                            stringResource(id = R.string.right_leg),
                            stringResource(id = R.string.left_leg)
                        ),
                        value = it.workingLegState.value,
                        onValueChange = { state.workingLegState.value = it },
                    )
                }
                Text(
                    text = "Контакти",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId = R.string.phone,
                    state = it,
                    value = state.phoneText.value,
                    onValueChange ={state.phoneText.value = it} ,
                    transformation = VisualTransformation.None,
                )
                CustomDropDownMenu(
                    labelResId = R.string.city,
                    listItems = ,
                    value = ,
                    onValueChange = )
                Spacer(modifier = Modifier.size(12.dp))
                CustomDropDownMenu(
                    labelResId =,
                    listItems =,
                    value = ,
                    onValueChange =
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Конфіденційність",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = "Налаштуйте видимість ваших персональних даних іншим користувачам",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(20.dp))
                Row {
                    Text(
                        text = stringResource(R.string.phone_num),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = lightGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        state = it,
                        onCheckedChange = { state.phoneNumberRadioButtonState.value = it },
                        selected = it.phoneNumberRadioButtonState.value,
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.email),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = lightGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        state = it,
                        onCheckedChange = { state.emailRadioButtonState.value = it },
                        selected = it.emailRadioButtonState.value,
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Row {
                    Text(
                        text = "Відгуки про мене",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = lightGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        state = it,
                        onCheckedChange = { state.myReviewsRadioButtonState.value = it },
                        selected = it.myReviewsRadioButtonState.value,
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Row {
                    Text(
                        text = stringResource(id = R.string.planned_events_1),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = lightGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        state = it,
                        onCheckedChange = { state.plannedEventsRadioButtonState.value = it },
                        selected = it.plannedEventsRadioButtonState.value,
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = avatarGrey, shape = RoundedCornerShape(8.dp))
                        .wrapContentHeight()
                        .background(
                            color = Color.White,
                            shape = shapes.medium,
                        )
                        .padding(start = 12.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "7 подій приховано",
                        fontSize = 13.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_change_data),
                        contentDescription = null,
                        tint = primaryDark
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = "Безпека",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Ви можете змінити свій логін, пароль",
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(width = 1.dp, color = avatarGrey, shape = RoundedCornerShape(8.dp))
                        .wrapContentHeight()
                        .background(
                            color = Color.White,
                            shape = shapes.medium,
                        )
                        .padding(start = 12.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "stefa.kalyna@gmail.com",
                        fontSize = 13.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_change_data),
                        contentDescription = null,
                        tint = primaryDark
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}