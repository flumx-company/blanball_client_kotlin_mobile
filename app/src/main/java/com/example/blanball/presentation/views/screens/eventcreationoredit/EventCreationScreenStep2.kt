package com.example.blanball.presentation.views.screens.eventcreationoredit

import DottedLine
import OutlineRadioButton
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.data.EventScreenMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.NoMatchForYouQueryBanner
import com.example.blanball.presentation.views.components.buttons.InvitedUsersOfTheEventButton
import com.example.blanball.presentation.views.components.buttons.NextAndPreviousButtonsHorizontal
import com.example.blanball.presentation.views.components.buttons.PreviewOfTheEventPosterButton
import com.example.blanball.presentation.views.components.cards.UserCardOnEventCreation
import com.example.blanball.presentation.views.components.textinputs.DefaultTextInput
import com.example.blanball.utils.ext.isNotValidMaxCountOfPlayers
import com.maxkeppeker.sheets.core.utils.BaseModifiers.dynamicContentWrapOrMaxHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventEditOrCreationScreenStep2(
    paddingValues: PaddingValues,
    state: UiState,
    navigateToThirdStep: () -> Unit,
    bottomDrawerPreviewContent: @Composable () -> Unit,
    invitedUsersModalContent: @Composable () -> Unit,
    backBtnCLicked: () -> Unit,
    usersSearchClicked: () -> Unit,
    isEditOrCreation: EventScreenMainContract.EditOrCreationState,
) {
    (state as? EventScreenMainContract.State)?.let { currentState ->
        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 12.dp)
            ) {
                Text(
                    text = if (
                        isEditOrCreation == EventScreenMainContract.EditOrCreationState.CREATION
                    ) stringResource(R.string.creation_event) else stringResource(id = R.string.edit_event),
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
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
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.do_you_want),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlineRadioButton(
                        onClick = {
                            currentState.isEventPrivacyStates.value =
                                EventScreenMainContract.EventPrivacyStates.NO
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                currentState.isEventPrivacyStates.value =
                                    EventScreenMainContract.EventPrivacyStates.NO
                            },
                        state = currentState,
                        text = stringResource(R.string.No_the_entrance_is_free),
                        selected = currentState.isEventPrivacyStates.value == EventScreenMainContract.EventPrivacyStates.NO,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            currentState.isEventPrivacyStates.value =
                                EventScreenMainContract.EventPrivacyStates.YES
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                currentState.isEventPrivacyStates.value =
                                    EventScreenMainContract.EventPrivacyStates.YES
                            },
                        state = currentState,
                        text = stringResource(R.string.yes_the_event_is_closed),
                        selected = currentState.isEventPrivacyStates.value ==
                                EventScreenMainContract.EventPrivacyStates.YES,
                        icon = null,
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.does_participation_in_the_event),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlineRadioButton(
                        onClick = {
                            currentState.priceStates.value =
                                EventScreenMainContract.PriceStates.FREE
                            currentState.eventSummaryPrice.value = null
                            currentState.priceDescription.value = null
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                currentState.priceStates.value =
                                    EventScreenMainContract.PriceStates.FREE

                            },
                        state = currentState,
                        text = stringResource(id = R.string.free),
                        selected = currentState.priceStates.value == EventScreenMainContract.PriceStates.FREE,
                        icon = null,
                    )
                    OutlineRadioButton(
                        onClick = {
                            currentState.priceStates.value =
                                EventScreenMainContract.PriceStates.PAID
                        },
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                currentState.priceStates.value =
                                    EventScreenMainContract.PriceStates.PAID
                            },
                        state = currentState,
                        text = stringResource(R.string.paid),
                        selected = currentState.priceStates.value ==
                                EventScreenMainContract.PriceStates.PAID,
                        icon = null,
                    )
                }
                if (currentState.priceStates.value == EventScreenMainContract.PriceStates.PAID) {
                    Spacer(modifier = Modifier.size(16.dp))
                    DefaultTextInput(
                        labelResId = R.string.summary_uah,
                        state = state,
                        value = currentState.eventSummaryPrice.value ?: "",
                        onValueChange = { currentState.eventSummaryPrice.value = it },
                        transformation = VisualTransformation.None,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cash),
                                contentDescription = null,
                                tint = primaryDark,
                            )
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        ),
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    DefaultTextInput(
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {},
                        textFieldModifier = Modifier
                            .fillMaxWidth()
                            .height(104.dp),
                        state = state,
                        isSingleLine = false,
                        value = state.priceDescription.value ?: "",
                        onValueChange = { state.priceDescription.value = it },
                        transformation = VisualTransformation.None,
                        labelResId = R.string.describ_what_this_amount_is_needed_for,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.invitation_of_participants),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    style = typography.h3,
                    fontWeight = FontWeight(700),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.specify_the_number),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = secondaryNavy,
                )
                Spacer(modifier = Modifier.size(20.dp))
                DefaultTextInput(
                    labelResId = R.string.max_50,
                    state = currentState,
                    value = currentState.maxEventPlayersState.value,
                    onValueChange = { state.maxEventPlayersState.value = it },
                    transformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_peoples),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    },
                    isError = when {
                        currentState.maxEventPlayersState.value.isNotValidMaxCountOfPlayers() -> true
                        else -> false
                    },
                    errorMessage = when {
                        currentState.maxEventPlayersState.value.isNotValidMaxCountOfPlayers() -> stringResource(
                            id = R.string.max_count_players_validation
                        )

                        else -> {
                            ""
                        }
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
                SearchBar(
                    modifier = Modifier
                        .heightIn(min = 1.dp, max = 260.dp)
                        .fillMaxWidth()
                        .border(
                            shape = RoundedCornerShape(size = 4.dp),
                            color = defaultLightGray,
                            width = 1.dp
                        )
                        .animateContentSize(),
                    query = currentState.usersSearchState.value,
                    onQueryChange = { searchText ->
                        state.usersSearchState.value = searchText
                        state.userSearchQuery.value = searchText
                        usersSearchClicked()
                    },
                    onSearch = {},
                    colors = SearchBarDefaults.colors(
                        inputFieldColors = SearchBarDefaults.inputFieldColors(
                            focusedTextColor = primaryDark,
                            cursorColor = mainGreen,
                        )
                    ),
                    shape = shapes.medium,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search_left),
                            contentDescription = null,
                            tint = primaryDark,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_add_user),
                            contentDescription = null,
                            tint = primaryDark,
                        )

                    },
                    active = currentState.isSearchColumnOpen.value,
                    onActiveChange = { state.isSearchColumnOpen.value = it },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.users_search),
                            fontSize = 14.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = primaryDark,
                        )
                    },
                ) {
                    if (currentState.state == EventScreenMainContract.ScreenViewState.UserSearchLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 40.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(color = mainGreen)
                        }
                    } else {
                        if (state.listOfFoundUsers.value.isNotEmpty()) {
                            LazyColumn(
                                modifier = Modifier
                                    .dynamicContentWrapOrMaxHeight(scope = this)
                                    .fillMaxWidth(),
                                content = {
                                    itemsIndexed(state.listOfFoundUsers.value) { _, user ->
                                        UserCardOnEventCreation(
                                            userAvatarUrl = user.profile.avatar_url ?: "",
                                            userFirstName = user.profile.name,
                                            userLastName = user.profile.last_name,
                                            userId = user.id,
                                            isUserSelected = currentState.selectedUserIds.value.contains(
                                                user.id
                                            ),
                                            onUserSelected = { userId ->
                                                if (!currentState.selectedUserIds.value.contains(
                                                        userId
                                                    )
                                                ) {
                                                    currentState.selectedUserIds.value += userId
                                                    currentState.selectedUserProfiles.value += user

                                                } else {
                                                    currentState.selectedUserIds.value -= userId
                                                    currentState.selectedUserProfiles.value -= user
                                                }
                                            },
                                        )
                                    }
                                }
                            )
                        } else {
                            NoMatchForYouQueryBanner()
                        }
                    }
                }
                DottedLine(color = defaultLightGray)
                Spacer(modifier = Modifier.size(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.progress),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = secondaryNavy,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = null,
                        tint = avatarGrey,
                    )
                    Text(
                        text = stringResource(R.string._2_3),
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = primaryDark,
                    )
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = secondaryNavy,
                    )
                }
                Row(
                    Modifier.padding(top = 20.dp)
                ) {
                    repeat(2) {
                        Image(
                            painter = painterResource(R.drawable.stepline_1),
                            contentDescription = null,
                            Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.size(2.dp))
                    }
                    Image(
                        painter = painterResource(id = R.drawable.empty_stepline),
                        contentDescription = null,
                        Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                NextAndPreviousButtonsHorizontal(
                    isEnabled = if (currentState.priceStates.value == EventScreenMainContract.PriceStates.PAID) {
                        !currentState.priceDescription.value.isNullOrEmpty() &&
                                !currentState.eventSummaryPrice.value.isNullOrEmpty() &&
                                currentState.maxEventPlayersState.value.isNotEmpty()
                    } else {
                        currentState.maxEventPlayersState.value.isNotEmpty()
                    },
                    nextBtnOnClick = { navigateToThirdStep() },
                    prevBtnOnClick = { backBtnCLicked() },
                    nextBtnOnTextId = R.string.next,
                    prevBtnOnTextId = R.string.back,
                )
                Spacer(modifier = Modifier.size(16.dp))
                PreviewOfTheEventPosterButton {
                    currentState.isBottomPreviewDrawerOpen.value = true
                }
                Spacer(modifier = Modifier.size(16.dp))
                InvitedUsersOfTheEventButton { currentState.isInvitedUsersDrawerOpen.value = true }
                when {
                    currentState.isBottomPreviewDrawerOpen.value -> bottomDrawerPreviewContent()
                    currentState.isInvitedUsersDrawerOpen.value -> invitedUsersModalContent()
                }
            }
        }
    }
}