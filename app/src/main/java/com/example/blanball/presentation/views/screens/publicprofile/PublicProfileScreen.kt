package com.example.blanball.presentation.views.screens.publicprofile

import DottedLine
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.DarkOverlay
import com.example.blanball.presentation.theme.accentLightGreen
import com.example.blanball.presentation.theme.annotationGray
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.successValidationGreen
import com.example.blanball.presentation.theme.successValidationGreenBG
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.AttentionText
import com.example.blanball.presentation.views.components.boxes.IcBox
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.colums.DisplayUserPlannedEventsColumn
import com.example.blanball.presentation.views.components.colums.DisplayUserReviewsColumn
import com.example.blanball.presentation.views.components.dropdownmenu.CustomDropDownMenu
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.textinputs.ReadOnlyOutlinePlaceholder
import com.example.blanball.utils.ext.formatRating
import com.example.blanball.utils.makeCall
import com.example.blanball.utils.writeEmail

@Composable
fun PublicProfileScreen(
    state: UiState,
    onInviteToAnEventClicked: () -> Unit,
    onAllReviewsScreenClicked: () -> Unit,
    onAllPlannedEventsScreenClicked: () -> Unit,
    paddingValues: PaddingValues
) {
    val currentState: PublicProfileMainContract.State =
        (state as? PublicProfileMainContract.State) ?: PublicProfileMainContract.State(
            PublicProfileMainContract.ScreenViewState.Idle
        )
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(paddingValues),
        contentAlignment = Alignment.TopCenter,
    ) {
        Image(
            painter = painterResource(id = R.drawable.public_profile_cover_blue),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )
        (state as? PublicProfileMainContract.State)?.let {
            Column {
                DefaultCardWithColumn(padStart = 0.dp, padTop = 0.dp, padEnd = 0.dp) {
                    Box(
                        Modifier
                            .background(color = accentLightGreen)
                            .fillMaxWidth(),
                        contentAlignment = Center,
                    ) {
                        Spacer(modifier = Modifier.size(4.dp))
                        Row {
                            Text(
                                text = if (state.userIsVerified.value)
                                    stringResource(id = R.string.verified)
                                else stringResource(id = R.string.unverified),
                                style = typography.h6,
                                color = mainGreen,
                            )
                            Spacer(modifier = Modifier.size(4.dp))
                            Image(
                                painter = painterResource(id = R.drawable.logo_green),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp, 16.dp)
                            )
                        }
                        Spacer(modifier = Modifier.size(4.dp))
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                        if (state.userAvatar.value.isNullOrEmpty()) {
                            Box(
                                modifier = Modifier.size(112.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.circle_avatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .fillMaxSize()
                                )
                                Text(
                                    text = "${state.userLastNameText.value.firstOrNull() ?: ""} ${state.userFirstNameText.value.firstOrNull() ?: ""}",
                                    modifier = Modifier.align(
                                        Center
                                    ),
                                    style = typography.h2, fontSize = 56.sp, color = mainGreen
                                )
                            }
                        } else {
                            Image(
                                painter = rememberAsyncImagePainter(state.userAvatar.value),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(144.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Column(horizontalAlignment = Start, modifier = Modifier.height(144.dp)) {
                            Text(
                                text = state.userLastNameText.value,
                                style = typography.h2,
                                fontSize = 20.sp,
                                color = primaryDark
                            )
                            Text(
                                text = state.userFirstNameText.value,
                                style = typography.h2, fontSize = 20.sp,
                                color = primaryDark,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.wrapContentWidth()
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "${state.userRoleText.value} / ${state.userPositionText.value}",
                                style = typography.h6,
                                color = primaryDark,
                                modifier = Modifier
                                    .wrapContentWidth()
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Row {
                                Text(
                                    text = state.rating.value.formatRating(),
                                    style = typography.subtitle2,
                                    fontSize = 22.sp,
                                    color = primaryDark,
                                )
                                Text(
                                    text = stringResource(id = R.string.five_scores),
                                    style = typography.h5,
                                    fontSize = 14.sp,
                                    color = secondaryNavy,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                                Spacer(modifier = Modifier.size(5.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.full_star),
                                    tint = mainGreen,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .size(17.92.dp, 17.08.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Button(
                        onClick = { it.openInviteUserToInventModal.value = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(start = 16.dp, end = 16.dp),
                        shape = shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = primaryDark,
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(
                            text = stringResource(id = R.string.invite_to_an_event),
                            style = typography.h5,
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    if (state.userEmail.value.isNotEmpty() || state.userPhoneNumberText.value.isNotEmpty()) {
                        Row(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            if (state.userEmail.value.isNotEmpty()) {
                                Button(
                                    onClick = {
                                        writeEmail(
                                            arrayOf(state.userEmail.value), context
                                        )
                                    },
                                    modifier = Modifier
                                        .height(45.dp)
                                        .weight(1f)
                                        .border(
                                            width = 1.dp,
                                            color = defaultLightGray,
                                            shape = shapes.medium
                                        ),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.White,
                                        contentColor = secondaryNavy,
                                    ),
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.mail_ic),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Text(
                                        text = stringResource(id = R.string.write_email),
                                        style = typography.h6,
                                    )
                                }
                            }
                            if (state.userPhoneNumberText.value.isNotEmpty()) {
                                Button(
                                    onClick = {
                                        makeCall(
                                            state.userPhoneNumberText.value,
                                            context
                                        )
                                    },
                                    modifier = Modifier
                                        .height(45.dp)
                                        .weight(1f)
                                        .border(
                                            width = 1.dp,
                                            color = defaultLightGray,
                                            shape = shapes.medium,
                                        ),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color.White,
                                        contentColor = secondaryNavy,
                                    )
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.phone_ic),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Text(
                                        text = stringResource(id = R.string.to_call),
                                        style = typography.h6,
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(18.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.qualification),
                            style = typography.h6,
                            color = annotationGray,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = if (state.userIsConfirmed.value)
                                stringResource(id = R.string.сonfirmed)
                            else stringResource(id = R.string.unсonfirmed),
                            style = typography.h6,
                            color = successValidationGreen,
                            textAlign = TextAlign.Start,
                            modifier = Modifier
                                .background(
                                    successValidationGreenBG,
                                    shapes.small
                                )
                                .padding(start = 4.dp, end = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(18.dp))
                    Text(
                        text = stringResource(id = R.string.about_youself),
                        style = typography.h5,
                        color = annotationGray,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = state.aboutUserText.value!!,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 16.dp, end = 16.dp),
                        style = typography.h5,
                        color = primaryDark,
                    )
                }
                DefaultCardWithColumn {
                    Text(
                        text = stringResource(id = R.string.game_stats),
                        style = typography.h2,
                        fontSize = 16.sp,
                        color = primaryDark
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    DottedLine(color = annotationGray)
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.wrapContentWidth()
                    ) {
                        IcBox(icon = R.drawable.ic_flag)
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.game_position),
                                style = typography.h6,
                                color = DarkOverlay,
                            )
                            Text(
                                text = state.userPositionText.value!!,
                                style = typography.h5,
                                color = primaryDark
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IcBox(icon = R.drawable.ic_dumbbell)
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.weight),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(
                                text = " ${state.userWeightText.value} ${stringResource(id = R.string.weight_meas_units)}",
                                style = typography.h5,
                                color = primaryDark
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IcBox(icon = R.drawable.ic_leg,)
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.kicking_leg),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(
                                text = state.userWorkingLegText.value!!,
                                style = typography.h5,
                                color = primaryDark
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        IcBox(icon = R.drawable.ic_ruler)
                        Column(Modifier.weight(1f)) {
                            Text(
                                text = stringResource(id = R.string.height),
                                style = typography.h6,
                                color = DarkOverlay
                            )
                            Text(
                                text = "${state.userHeightText.value} ${stringResource(id = R.string.height_meas_units)}",
                                style = typography.h5,
                                color = primaryDark
                            )
                        }
                    }
                }
                DefaultCardWithColumn {
                    Text(
                        text = stringResource(id = R.string.ratings_and_reviews),
                        style = typography.h2,
                        color = primaryDark,
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "${state.reviewsCount.value} ${stringResource(id = R.string.grades)}",
                        style = typography.h6,
                        color = selectedDarkGray
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    if (state.reviewsList.value.isEmpty()) {
                        AttentionText(
                            text = "${state.userLastNameText.value} ${state.userFirstNameText.value} ${
                                stringResource(
                                    id = R.string.have_not_yet_received_reviews
                                )
                            }"
                        )
                    } else {
                        DisplayUserReviewsColumn(it)
                        Text(
                            stringResource(id = R.string.show_all_reviews),
                            style = typography.h6,
                            color = secondaryNavy,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable(onClick = onAllReviewsScreenClicked)
                        )
                    }
                }
                DefaultCardWithColumn {
                    Text(
                        text = stringResource(id = R.string.planned_submissions),
                        style = typography.h2,
                        color = primaryDark,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "${state.plannedEventsCount.value} ${stringResource(id = R.string.planned_events)}",
                        style = typography.h6,
                        color = selectedDarkGray
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    if (state.plannedEventsList.value.isEmpty()) {
                        AttentionText(
                            text = "${state.userLastNameText.value} ${state.userFirstNameText.value} ${
                                stringResource(
                                    id = R.string.have_not_yet_received_planned_events
                                )
                            }"
                        )
                    } else {
                        DisplayUserPlannedEventsColumn(it)
                        Text(
                            stringResource(id = R.string.show_all_planned_events),
                            style = typography.h6,
                            color = secondaryNavy,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable(onClick = onAllPlannedEventsScreenClicked)
                        )
                    }
                    Spacer(modifier = Modifier.size(12.dp))
                    DottedLine(color = annotationGray)
                    Spacer(modifier = Modifier.size(12.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = stringResource(R.string.history_of_participation_in_events),
                            style = typography.h2,
                            color = primaryDark,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_2),
                            contentDescription = null,
                            tint = secondaryNavy
                        )
                    }
                }
                Spacer(modifier = Modifier.size(32.dp))
            }
            if (it.openInviteUserToInventModal.value) {
                AlertDialog(
                    modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 30.dp),
                    shape = RoundedCornerShape(6.dp),
                    backgroundColor = Color.White,
                    title = {
                        Column {
                            Text(
                                text = stringResource(id = R.string.invite_to_an_event),
                                style = typography.h5,
                                lineHeight = 24.sp,
                                fontSize = 14.sp,
                                color = primaryDark,
                            )
                            Text(
                                text = stringResource(id = R.string.you_are_going_to_invite),
                                style = typography.h6,
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                color = secondaryNavy,
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = { /*TODO*/ },
                            shape = shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = mainGreen,
                                contentColor = Color.White,
                            ),
                        ) {
                            Text(
                                text = stringResource(id = R.string.invite),
                                style = typography.h5
                            )
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { it.openInviteUserToInventModal.value = false },
                            modifier = Modifier.align(alignment = Alignment.CenterStart)
                        ) {
                            Text(
                                text = stringResource(id = R.string.cancel),
                                style = typography.h4,
                                fontSize = 14.sp,
                                color = secondaryNavy,
                            )
                        }
                    },
                    onDismissRequest = {
                    },
                    text = {
                        Column {
                            Spacer(modifier = Modifier.size(16.dp))
                            CustomDropDownMenu(
                                labelResId = R.string.choose_the_ivent,
                                listItems = it.invitesList.value,
                                value = it.selectedInviteState.value,
                                onValueChange = { state.selectedInviteState.value = it },
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                            ReadOnlyOutlinePlaceholder(
                                modifier = Modifier.fillMaxWidth(1f),
                                value = it.addMessageState.value,
                                onValueChange = { state.addMessageState.value = it },
                                labelResId = R.string.message,
                                trailingIconRedId = R.drawable.ic_clip
                            )
                            Spacer(modifier = Modifier.size(16.dp))
                        }
                    }
                )
            }
        }
    }
        if (currentState.state is PublicProfileMainContract.ScreenViewState.Loading) {
            Loader(backgroundColor = Color.White, textColor = primaryDark)
        }
}