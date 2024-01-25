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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.ChangePassButton
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsHorizontal
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.cards.MyRatingCard
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.modals.LookProfileFromTheSideModal
import com.example.blanball.presentation.views.components.switches.SwitchButton
import com.example.blanball.presentation.views.components.tabrows.TabRow
import com.example.blanball.presentation.views.components.textinputs.BottomLineDefaultTextInput
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.texts.MyProfileMainGreenTextBadge
import com.example.blanball.utils.ext.calculateAge
import com.example.blanball.utils.ext.formatEnglishAbbreviationToPosition
import com.example.blanball.utils.ext.isNotValidBirthDay
import com.example.blanball.utils.ext.isNotValidBirthMonth
import com.example.blanball.utils.ext.isNotValidBirthYear
import com.example.blanball.utils.ext.isNotValidHeight
import com.example.blanball.utils.ext.isNotValidWeight
import com.example.blanball.utils.ext.toBirthdayDay

@Composable
fun EditMyProfileScreen(
    state: UiState,
    paddingValues: PaddingValues,
    onBackClicked: () -> Unit,
    onNavigateToDemoClicked: () -> Unit,
    onSimpleSaveClicked: () -> Unit,
    onCancelEditsClicked: () -> Unit,
    onUpdateCitiesForRegionList: () -> Unit,
) {
    val context = LocalContext.current
    val localFocusManager = LocalFocusManager.current
    val icons: List<Painter> = listOf(
        painterResource(id = R.drawable.ic_user),
        painterResource(id = R.drawable.ic_rate)
    )

    val tabs: List<String> = listOf(
        stringResource(id = R.string.my_profile),
        stringResource(R.string.rate_plan),
    )
    (state as? MyProfileScreensMainContract.State)?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (state.isModalOpen.value) {
                LookProfileFromTheSideModal(
                    onNavigateToDemoClicked = {
                        onNavigateToDemoClicked()
                        state.isModalOpen.value = false
                    },
                    onSimpleSaveClicked = {
                        onSimpleSaveClicked()
                        state.isModalOpen.value = false
                    },
                    onCancelEditsClicked = {
                        onCancelEditsClicked()
                        state.isModalOpen.value = false
                    },
                    onCloseModalClicked = { state.isModalOpen.value = false },
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
            )
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
                    text = stringResource(id = R.string.update_your_photo_and_personal_data),
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
                                    text = "${state.myLastNameText.value.firstOrNull() ?: ""}${state.myFirstNameText.value.firstOrNull() ?: ""}",
                                    modifier = Modifier.align(
                                        Alignment.Center
                                    ),
                                    style = typography.h2, fontSize = 22.sp, color = mainGreen
                                )
                            }
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(state.myAvatarUrl.value),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(RoundedCornerShape(4.dp)),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Column {
                            Text(
                                text = "${state.myFirstNameText.value} ${state.myLastNameText.value}",
                                fontSize = 18.sp,
                                lineHeight = 24.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(700),
                                color = primaryDark,
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Row {
                                MyProfileMainGreenTextBadge(text = state.roleState.value)
                                Spacer(modifier = Modifier.size(4.dp))
                                MyProfileMainGreenTextBadge(
                                    text = "${state.birthdayState.value.calculateAge()} ${
                                        stringResource(id = R.string.years)
                                    }"
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                MyProfileMainGreenTextBadge(
                                    text =
                                    state.myGenderState.value
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(16.dp))
                MyRatingCard(ratingValue = state.ratingState.value)
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
                    state = it,
                    value = it.myLastNameText.value,
                    onValueChange = { state.myLastNameText.value = it },
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId = R.string.your_firstname,
                    state = it,
                    value = it.myFirstNameText.value,
                    onValueChange = { state.myFirstNameText.value = it },
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId = R.string.a_few_words_about_me,
                    state = it,
                    value = it.aboutMeText.value ?: "",
                    onValueChange = { state.aboutMeText.value = it },
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    BottomLineDefaultTextInput(
                        labelResId = R.string.day,
                        modifier = Modifier.weight(1f),
                        value = it.editDayBirthdayState.value.toBirthdayDay(),
                        onValueChange = { state.editDayBirthdayState.value = it },
                        state = it,
                        transformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        isError = when {
                            it.editDayBirthdayState.value.isNotValidBirthDay() -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.editDayBirthdayState.value.isNotValidBirthDay() -> stringResource(id = R.string.birth_day_valid_error)
                            else -> {
                                ("")
                            }
                        }
                    )
                    BottomLineDefaultTextInput(
                        labelResId = R.string.month,
                        value = it.editMonthBirthdayState.value,
                        modifier = Modifier.weight(1f),
                        state = it,
                        transformation = VisualTransformation.None,
                        onValueChange = { state.editMonthBirthdayState.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        isError = when {
                            it.editMonthBirthdayState.value.isNotValidBirthMonth() -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.editMonthBirthdayState.value.isNotValidBirthMonth() -> stringResource(
                                id = R.string.birth_month_valid_error
                            )

                            else -> {
                                ("")
                            }
                        }
                    )
                    BottomLineDefaultTextInput(
                        labelResId = R.string.year,
                        state = it,
                        modifier = Modifier.weight(1f),
                        transformation = VisualTransformation.None,
                        value = it.editYearBirthdayState.value,
                        onValueChange = { state.editYearBirthdayState.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() }),
                        isError = when {
                            it.editYearBirthdayState.value.isNotValidBirthYear() -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.editYearBirthdayState.value.isNotValidBirthYear() -> stringResource(
                                id = R.string.birth_year_valid_error
                            )

                            else -> {
                                ("")
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = stringResource(id = R.string.game_stats),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DefaultTextInput(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth(),
                        state = it,
                        labelResId = R.string.height,
                        value = it.heightState.value ?: "",
                        onValueChange = { state.heightState.value = it },
                        transformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                        isError = when {
                            it.heightState.value.isNotValidHeight() -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.heightState.value.isNotValidHeight() -> stringResource(id = R.string.height_valid_error)
                            else -> {
                                ("")
                            }
                        }
                    )
                    DefaultTextInput(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxWidth(),
                        state = it,
                        labelResId = R.string.weight,
                        value = it.weightState.value ?: "",
                        onValueChange = { state.weightState.value = it },
                        transformation = VisualTransformation.None,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() }),
                        isError = when {
                            it.weightState.value.isNotValidWeight() -> true
                            else -> false
                        },
                        errorMessage = when {
                            it.weightState.value.isNotValidWeight() -> stringResource(id = R.string.weight_valid_error)
                            else -> {
                                ("")
                            }
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
                Spacer(modifier = Modifier.size(12.dp))
                CustomDropDownMenu(
                    modifier = Modifier
                        .fillMaxWidth(),
                    labelResId = R.string.game_position,
                    listItems = listOf(
                        stringResource(id = R.string.any_position),
                        stringResource(id = R.string.goalkeeper),
                        stringResource(id = R.string.right_defender),
                        stringResource(id = R.string.left_defender),
                        stringResource(id = R.string.central_defender),
                        stringResource(id = R.string.left_flank_defender),
                        stringResource(id = R.string.right_flank_defender),
                        stringResource(id = R.string.supporting_mid_defender),
                        stringResource(id = R.string.left_mid_defender),
                        stringResource(id = R.string.attacking_mid_defender),
                        stringResource(id = R.string.right_winger),
                        stringResource(id = R.string.left_winger),
                        stringResource(id = R.string.right_flank_attacker),
                        stringResource(id = R.string.left_flank_attacker),
                        stringResource(id = R.string.central_forward),
                        stringResource(id = R.string.left_forward),
                        stringResource(id = R.string.right_forward),
                        stringResource(id = R.string.forward_striker),
                    ),
                    value = it.positionState.value.formatEnglishAbbreviationToPosition(context = context),
                    onValueChange = { state.positionState.value = it },
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = stringResource(R.string.contacts),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                DefaultTextInput(
                    labelResId = R.string.phone,
                    state = it,
                    value = state.phoneText.value ?: "",
                    onValueChange = { state.phoneText.value = it },
                    transformation = VisualTransformation.None,
                )
                Spacer(modifier = Modifier.size(12.dp))
                CustomDropDownMenu(
                    labelResId = R.string.region,
                    listItems = it.regionOfUkraineList.value,
                    value = it.selectedRegion.value,
                    onValueChange = {
                        state.selectedRegion.value = it
                        onUpdateCitiesForRegionList()
                    }
                )
                Spacer(modifier = Modifier.size(12.dp))
                CustomDropDownMenu(
                    labelResId = R.string.city,
                    listItems = it.citiesForRegionList.value,
                    value = it.selectedCity.value,
                    onValueChange = { state.selectedCity.value = it }
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(
                    text = stringResource(R.string.privacy),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(id = R.string.adjust_the_visibility),
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
                        color = primaryDark,
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
                        color = primaryDark,
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
                        text = stringResource(R.string.reviews_about_me),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        state = it,
                        onCheckedChange = { state.myReviewsRadioButtonState.value = it },
                        selected = it.myReviewsRadioButtonState.value,
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
                        text = "7 " + stringResource(id = R.string.event_are_hint),
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
                    text = stringResource(R.string.safety),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(R.string.you_can_change_your_login_and_pass),
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
                        text = it.emailStringState.value,
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
                ChangePassButton {} //TODO()
                Spacer(modifier = Modifier.size(20.dp))
                Divider(color = defaultLightGray)
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.how_look_my_prof_for_others),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = secondaryNavy,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(8.dp))
                NextAndPreviousButtonsHorizontal(
                    isEnabled = state.editYearBirthdayState.value.isNotEmpty() && state.editMonthBirthdayState.value.isNotEmpty() && state.editDayBirthdayState.value.isNotEmpty(),
                    nextBtnOnClick = {
                        state.isModalOpen.value = true
                    },
                    prevBtnOnClick = { onBackClicked() },
                    nextBtnOnTextId = R.string.save,
                    prevBtnOnTextId = R.string.cancel,
                    cancelButtonColor = mainGreen,
                    borderCancelButtonColor = mainGreen,
                )
            }
        }
    }
}