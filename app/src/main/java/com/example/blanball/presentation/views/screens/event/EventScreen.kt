package com.example.blanball.presentation.views.screens.event

import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.bgItemsGray
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.classicBlue
import com.example.blanball.presentation.theme.classicRed
import com.example.blanball.presentation.theme.classicYellow
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.NoHaveUsersBanner
import com.example.blanball.presentation.views.components.buttons.EventBottomButtons
import com.example.blanball.presentation.views.components.cards.ErrorMessageCard
import com.example.blanball.presentation.views.components.cards.FanOnEventCard
import com.example.blanball.presentation.views.components.cards.PlayerOnEventCard
import com.example.blanball.presentation.views.components.cards.SuccessMessageCard
import com.example.blanball.presentation.views.components.cards.UserCardWithPhone
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.presentation.views.components.maps.SelectLocationWithGoogleMap
import com.example.blanball.presentation.views.components.tabrows.EventTabRow
import com.example.blanball.presentation.views.components.texts.TextBadge2
import com.example.blanball.utils.ext.formatTimeRange
import com.example.blanball.utils.ext.formatToUkrainianDate

@Composable
fun EventScreen(
    state: UiState,
    paddingValues: PaddingValues,
    verificationModalScreenContent: @Composable () -> Unit,
    shareLinkModalScreenContent: @Composable () -> Unit,
    isConfirmReminderContent: @Composable () -> Unit,
    isVerificationModalVisible: MutableState<Boolean>,
    isShareLinkModalVisible: MutableState<Boolean>,
    onNavigateToEventAuthorPublicProfile: () -> Unit,
    isConfirmReminderVisible: Boolean,
    onEditClick: (currentEventId: Int) -> Unit,
    onJoinBtnAsFunClick: () -> Unit,
    onJoinBtnAsPlayerClick: () -> Unit,
    onClickedToPublicProfile: (userId: Int) -> Unit,
    onCancelParticipation: () -> Unit,
) {
    (state as? EventScreenMainContract.State)?.let { currentState ->
        Box(modifier = Modifier.fillMaxSize()) {
            if (currentState.state == EventScreenMainContract.ScreenViewState.Loading) {
                Loader(backgroundColor = Color.White, textColor = primaryDark)
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .animateContentSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                    ) {
                        when {
                            isVerificationModalVisible.value -> verificationModalScreenContent()
                            isShareLinkModalVisible.value -> shareLinkModalScreenContent()
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 0.dp)
                    ) {
                        if (!isConfirmReminderVisible) {
                            isConfirmReminderContent()
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Text(
                            text = currentState.eventName.value,
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            style = typography.h3,
                            fontWeight = FontWeight(700),
                            color = primaryDark,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = stringResource(id = R.string.friendly_match),
                            fontSize = 13.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(500),
                            color = secondaryNavy,
                        )
                        Spacer(modifier = Modifier.size(22.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_clock),
                                contentDescription = null,
                                tint = primaryDark,
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = currentState.eventDateAndTime.value.formatToUkrainianDate(),
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = primaryDark,
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = currentState.eventDateAndTime.value.formatTimeRange(
                                    currentState.eventDuration.value
                                ), //TODO()
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = primaryDark,
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_location),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = currentState.eventPlaceName.value,
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = primaryDark,
                                textDecoration = TextDecoration.Underline,
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .clickable {
                                    currentState.isEventDescriptionVisible.value =
                                        !currentState.isEventDescriptionVisible.value
                                },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                modifier = Modifier,
                                painter = painterResource(id = R.drawable.ic_info),
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = stringResource(R.string.сost_of_participation),
                                fontSize = 14.sp,
                                lineHeight = 16.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(400),
                                color = primaryDark,
                                textDecoration = TextDecoration.Underline
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = if (currentState.eventPrice.value == 0) {
                                    stringResource(id = R.string.free)
                                } else {
                                    currentState.eventPrice.value.toString() + "" + stringResource(
                                        R.string.uah_char
                                    )
                                },
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                style = typography.h3,
                                fontWeight = FontWeight(700),
                                color = primaryDark,
                            )
                        }
                        Box(modifier = Modifier.animateContentSize()) {
                            Column {
                                if (!currentState.priceDescription.value.isNullOrEmpty() && currentState.isEventDescriptionVisible.value) {
                                    Spacer(modifier = Modifier.size(16.dp))
                                    Box(
                                        Modifier
                                            .shadow(
                                                elevation = 15.dp,
                                                spotColor = Color(0x1A081B82),
                                                ambientColor = Color(0x1A081B82)
                                            )
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                            .background(
                                                color = Color(0xFF575775),
                                                shape = RoundedCornerShape(size = 6.dp)
                                            )
                                            .padding(
                                                start = 6.dp,
                                                top = 4.dp,
                                                end = 6.dp,
                                                bottom = 4.dp
                                            )
                                    ) {
                                        Column {
                                            Text(
                                                text = stringResource(R.string.price_description),
                                                fontSize = 12.sp,
                                                lineHeight = 16.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(600),
                                                color = Color.White,
                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Text(
                                                text = currentState.priceDescription.value
                                                    ?: "",
                                                fontSize = 12.sp,
                                                lineHeight = 16.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(449),
                                                color = Color(0xFFEFEFEF),
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        Text(
                            text = stringResource(R.string.description_of_the_event),
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            modifier = Modifier
                                .animateContentSize()
                                .clickable {
                                    currentState.isDescriptionTextExpanded.value =
                                        !currentState.isDescriptionTextExpanded.value
                                },
                            text = currentState.eventDescription.value,
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = primaryDark,
                            maxLines = if (currentState.isDescriptionTextExpanded.value) Int.MAX_VALUE else 4,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            TextBadge2(text = currentState.sportType.value)
                            TextBadge2(text = currentState.eventGenders.value)
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        Box(
                            Modifier
                                .background(color = bgLight, shape = shapes.medium)
                                .padding(
                                    start = 12.dp,
                                    top = 8.dp,
                                    end = 12.dp,
                                    bottom = 12.dp,
                                )
                                .fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = stringResource(R.string.first_team),
                                    fontSize = 14.sp,
                                    lineHeight = 24.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(600),
                                    color = primaryDark,
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Row {
                                    Box(modifier = Modifier.weight(1f)) {
                                        Column {
                                            Text(
                                                text = stringResource(R.string.t_shirts),
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(500),
                                                color = secondaryNavy,
                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(
                                                        color = bgItemsGray,
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .padding(
                                                        start = 8.dp,
                                                        top = 2.dp,
                                                        end = 8.dp,
                                                        bottom = 2.dp
                                                    )
                                            ) {
                                                Row {
                                                    Box(
                                                        Modifier
                                                            .background(
                                                                color = Color.White,
                                                                shape = RoundedCornerShape(2.dp)
                                                            )
                                                            .padding(2.dp)
                                                    ) {
                                                        Box(
                                                            Modifier
                                                                .size(12.dp)
                                                                .background(color = classicBlue)
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.size(8.dp))
                                                    Text(
                                                        text = stringResource(R.string.blue), //TODO()
                                                        fontSize = 12.sp,
                                                        lineHeight = 20.sp,
                                                        style = typography.h4,
                                                        fontWeight = FontWeight(500),
                                                        color = primaryDark,
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Box(modifier = Modifier.weight(1f)) {
                                        Column {
                                            Text(
                                                text = stringResource(R.string.shorts),
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(500),
                                                color = secondaryNavy,
                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(
                                                        color = bgItemsGray,
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .padding(
                                                        start = 8.dp,
                                                        top = 2.dp,
                                                        end = 8.dp,
                                                        bottom = 2.dp
                                                    )
                                            ) {
                                                Row {
                                                    Box(
                                                        Modifier
                                                            .background(
                                                                color = Color.White,
                                                                shape = RoundedCornerShape(2.dp)
                                                            )
                                                            .padding(2.dp)
                                                    ) {
                                                        Box(
                                                            Modifier
                                                                .size(12.dp)
                                                                .background(color = classicRed)
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.size(8.dp))
                                                    Text(
                                                        text = stringResource(R.string.red),
                                                        fontSize = 12.sp,
                                                        lineHeight = 20.sp,
                                                        style = typography.h4,
                                                        fontWeight = FontWeight(500),
                                                        color = primaryDark,
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Box(
                            Modifier
                                .background(color = bgLight, shape = shapes.medium)
                                .padding(
                                    start = 12.dp,
                                    top = 8.dp,
                                    end = 12.dp,
                                    bottom = 12.dp,
                                )
                                .fillMaxWidth()
                        ) {
                            Column {
                                Text(
                                    text = stringResource(R.string.second_team),
                                    fontSize = 14.sp,
                                    lineHeight = 24.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(600),
                                    color = primaryDark,
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Row {
                                    Box(modifier = Modifier.weight(1f)) {
                                        Column {
                                            Text(
                                                text = stringResource(R.string.t_shirts),
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(500),
                                                color = secondaryNavy,
                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(
                                                        color = bgItemsGray,
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .padding(
                                                        start = 8.dp,
                                                        top = 2.dp,
                                                        end = 8.dp,
                                                        bottom = 2.dp
                                                    )
                                            ) {
                                                Row {
                                                    Box(
                                                        Modifier
                                                            .background(
                                                                color = Color.White,
                                                                shape = RoundedCornerShape(2.dp)
                                                            )
                                                            .padding(2.dp)
                                                    ) {
                                                        Box(
                                                            Modifier
                                                                .size(12.dp)
                                                                .background(color = classicYellow)
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.size(8.dp))
                                                    Text(
                                                        text = stringResource(R.string.yellow), //TODO()
                                                        fontSize = 12.sp,
                                                        lineHeight = 20.sp,
                                                        style = typography.h4,
                                                        fontWeight = FontWeight(500),
                                                        color = primaryDark,
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.size(8.dp))
                                    Box(modifier = Modifier.weight(1f)) {
                                        Column {
                                            Text(
                                                text = stringResource(R.string.shorts),
                                                fontSize = 12.sp,
                                                lineHeight = 20.sp,
                                                style = typography.h4,
                                                fontWeight = FontWeight(500),
                                                color = secondaryNavy,
                                            )
                                            Spacer(modifier = Modifier.size(4.dp))
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .background(
                                                        color = bgItemsGray,
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .padding(
                                                        start = 8.dp,
                                                        top = 2.dp,
                                                        end = 8.dp,
                                                        bottom = 2.dp
                                                    )
                                            ) {
                                                Row {
                                                    Box(
                                                        Modifier
                                                            .background(
                                                                color = Color.White,
                                                                shape = RoundedCornerShape(2.dp)
                                                            )
                                                            .padding(2.dp)
                                                    ) {
                                                        Box(
                                                            Modifier
                                                                .size(12.dp)
                                                                .background(color = Color.White)
                                                                .border(
                                                                    width = 1.dp,
                                                                    shape = RoundedCornerShape(2.dp),
                                                                    color = defaultLightGray
                                                                )
                                                        )
                                                    }
                                                    Spacer(modifier = Modifier.size(8.dp))
                                                    Text(
                                                        text = stringResource(R.string.white), //TODO()
                                                        fontSize = 12.sp,
                                                        lineHeight = 20.sp,
                                                        style = typography.h4,
                                                        fontWeight = FontWeight(500),
                                                        color = primaryDark,
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.size(20.dp))
                        UserCardWithPhone(
                            userAvatarUrl = currentState.eventAuthorAvatar.value,
                            firstName = currentState.eventAuthorFirstName.value,
                            lastName = currentState.eventAuthorLastName.value,
                            userPhone = currentState.eventAuthorPhone.value,
                            userRole = stringResource(R.string.organizer),
                            clickCallback = { onNavigateToEventAuthorPublicProfile() },
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        SelectLocationWithGoogleMap(
                            isClickable = true,
                            eventLocationLatLng = currentState.eventLatLng,
                            isMarkerVisible = true,
                            isLongClickAvailable = false,
                            height = 128.dp,
                        )
                        Spacer(modifier = Modifier.size(36.dp))
                        Text(
                            text = stringResource(R.string.already_confirme_participation),
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            style = typography.h3,
                            fontWeight = FontWeight(700),
                            color = primaryDark,
                        )
                        EventTabRow(
                            tabs =
                            if (currentState.isMyEvent.value) {
                                listOf(
                                    stringResource(R.string.users_list),
                                    stringResource(R.string.registered_viewers),
//                                    stringResource(R.string.requests_for_participation),
                                )
                            } else {
                                listOf(
                                    stringResource(R.string.users_list),
                                    stringResource(R.string.registered_viewers),
                                )
                            },
                            icons =
                            if (currentState.isMyEvent.value) {
                                listOf(
                                    painterResource(id = R.drawable.ic_ball),
                                    painterResource(id = R.drawable.ic_peoples),
//                                    painterResource(id = R.drawable.ic_add_user)
                                )
                            } else {
                                listOf(
                                    painterResource(id = R.drawable.ic_ball),
                                    painterResource(id = R.drawable.ic_peoples),
                                )
                            },
                            contentItems = listOf(
                                {
                                    if (currentState.invitedPlayersList.value.isEmpty()) {
                                        NoHaveUsersBanner(
                                            headerTextId = R.string.no_users_to_display,
                                            secTextId = R.string.we_will_notify_you_users,
                                        )
                                    } else {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight()
                                                .background(
                                                    color = Color.White,
                                                    shape = shapes.medium
                                                )
                                                .border(
                                                    width = 1.dp,
                                                    color = mainGreen,
                                                    shape = shapes.medium
                                                )
                                                .padding(vertical = 24.dp, horizontal = 12.dp)
                                        ) {
                                            PlayerOnEventCard(
                                                clickCallback = { userId ->
                                                    onClickedToPublicProfile(
                                                        userId
                                                    )
                                                },
                                                invitedPlayersList = currentState.invitedPlayersList.value,
                                            )
                                        }
                                    }
                                },
                                {
                                    if (currentState.invitedFansList.value.isEmpty()) {
                                        NoHaveUsersBanner(
                                            headerTextId = R.string.no_fans_to_display,
                                            secTextId = R.string.we_will_notify_you_fan,
                                        )
                                    } else {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight()
                                                .background(
                                                    color = Color.White,
                                                    shape = shapes.medium
                                                )
                                                .border(
                                                    width = 1.dp,
                                                    color = mainGreen,
                                                    shape = shapes.medium
                                                )
                                                .padding(vertical = 24.dp, horizontal = 12.dp)
                                        ) {
                                            FanOnEventCard(
                                                invitedFansList = currentState.invitedFansList.value,
                                                clickCallback = { userId ->
                                                    onClickedToPublicProfile(
                                                        userId
                                                    )
                                                }
                                            )
                                        }
                                    }
                                },
                            )
                        )
                        Spacer(modifier = Modifier.size(20.dp))

                    }
                    EventBottomButtons(
                        onEditClick = { currentState.currentEventId.value?.let { onEditClick(it) } },
                        shareBtnClick = { isShareLinkModalVisible.value = true },
                        isMyEvent = currentState.isMyEvent.value,
                        onJoinBtnAsPlayerClick = { onJoinBtnAsPlayerClick() },
                        onJoinBtnAsFunClick = { onJoinBtnAsFunClick() },
                        state = state,
                        onCancelParticipation = { onCancelParticipation() },
                    )
                }
            }
            SuccessMessageCard(
                text = stringResource(R.string.you_have_successfully_joined_as) + " " + currentState.currentUserRole.value,
                isMessageVisible = currentState.isSuccessJoinMessageVisible.value,
                onCancelIconClicked = {
                    currentState.isSuccessJoinMessageVisible.value = false
                }
            )
            SuccessMessageCard(
                text = stringResource(R.string.you_have_successfully_leaved),
                isMessageVisible = currentState.isSuccessLeaveMessageVisible.value,
                onCancelIconClicked = {
                    currentState.isSuccessLeaveMessageVisible.value = false
                }
            )
            ErrorMessageCard(
                text = currentState.errorMessageText.value,
                isMessageVisible = currentState.isErrorMessageVisible.value,
                onCancelIconClicked = {
                    currentState.isErrorMessageVisible.value = false
                }
            )
        }
    }
}