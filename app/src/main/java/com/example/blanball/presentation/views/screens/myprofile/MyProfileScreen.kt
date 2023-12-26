package com.example.blanball.presentation.views.screens.myprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.errorRed
import com.example.blanball.presentation.theme.lightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.buttons.ChangePassButton
import com.example.blanball.presentation.views.components.buttons.EditProfileButton
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.cards.MyRatingCard
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.switches.SwitchButton
import com.example.blanball.presentation.views.components.tabrows.TabRow
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.presentation.views.components.texts.MyProfileMainGreenTextBadge
import com.example.blanball.utils.ext.calculateAge
import com.example.blanball.utils.ext.toFormattedBirthdayDate

@Composable
fun MyProfileScreen(
    state: UiState,
    paddingValues: PaddingValues,
    editProfileButtonClicked: () -> Unit,
    exitBtnClicked: () -> Unit,
    deleteAccBtnClicked: () -> Unit,
) {
    val icons: List<Painter> = listOf(
        painterResource(id = R.drawable.ic_user),
        painterResource(id = R.drawable.ic_rate)
    )

    val tabs: List<String> = listOf(
        stringResource(id = R.string.my_profile),
        stringResource(R.string.rate_plan),
    )

    val currentState: MyProfileScreensMainContract.State =
        (state as? MyProfileScreensMainContract.State) ?: MyProfileScreensMainContract.State(
            MyProfileScreensMainContract.ScreenViewState.Idle
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        (state as? MyProfileScreensMainContract.State)?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
            ) {
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
                    text = stringResource(R.string.update_your_photo_and_personal_data),
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
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = state.aboutMeText.value ?: "--",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(R.string.about_youself2),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Divider(color = defaultLightGray)
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = state.birthdayState.value.toFormattedBirthdayDate() ?: "",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(R.string.date_of_birthday),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(id = R.string.game_stats),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${state.heightState.value} ${stringResource(id = R.string.height_meas_units)}",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = primaryDark,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = stringResource(id = R.string.height),
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                        )
                    }
                    Divider(
                        color = defaultLightGray, modifier = Modifier
                            .height(44.dp)
                            .width(1.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${state.weightState.value} ${stringResource(id = R.string.weight_meas_units)}",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = primaryDark,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = stringResource(id = R.string.weight),
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                        )
                    }
                    Divider(
                        color = defaultLightGray, modifier = Modifier
                            .height(44.dp)
                            .width(1.dp)
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = state.workingLegState.value ?: "--",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = primaryDark,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = stringResource(id = R.string.kicking_leg),
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                Divider(color = defaultLightGray)
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = state.positionState.value ?: "--",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(id = R.string.game_position),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(id = R.string.contacts),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = state.phoneText.value ?: "--",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(R.string.phone),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Divider(color = defaultLightGray)
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = state.placeState.value,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(R.string.location),
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(id = R.string.privacy),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.adjust_the_visibility),
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
                        enabled = false,
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
                        enabled = false,
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
                        color = lightGray,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    SwitchButton(
                        enabled = false,
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
                        enabled = false,
                        state = it,
                        onCheckedChange = { state.plannedEventsRadioButtonState.value = it },
                        selected = it.plannedEventsRadioButtonState.value,
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(40.dp)
                        .border(width = 1.dp, color = avatarGrey, shape = RoundedCornerShape(8.dp))
                        .fillMaxWidth()
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
                    text = stringResource(id = R.string.safety),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = stringResource(id = R.string.you_can_change_your_login_and_pass),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(12.dp))
                DefaultTextInput(
                    labelResId = R.string.email,
                    state = it,
                    value = it.emailStringState.value,
                    onValueChange = { state.emailStringState.value = it },
                    transformation = VisualTransformation.None,
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_change_data),
                            contentDescription = null,
                            tint = primaryDark
                        )
                    }
                )
                Spacer(modifier = Modifier.size(12.dp))
                ChangePassButton {}
                Spacer(modifier = Modifier.size(16.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clickable { exitBtnClicked() }
                    .padding(top = 12.dp, bottom = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.exit_from_acc),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = secondaryNavy,
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable { deleteAccBtnClicked() }
                        .padding(top = 12.dp, bottom = 12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.delete_acc),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = errorRed,
                    )
                }
                Spacer(modifier = Modifier.size(44.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    EditProfileButton {
                        editProfileButtonClicked()
                    }
                }
            }
        }
        if (currentState.state is MyProfileScreensMainContract.ScreenViewState.Loading) {
            Loader(backgroundColor = Color.White, textColor = primaryDark)
        }
    }
}