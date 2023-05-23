package com.example.blanball.presentation.views.screens.publicprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.PublicProfileMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.DarkOverlay
import com.example.blanball.presentation.theme.annotationGray
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.orangeStarColor
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.widgets.colums.DisplayUserReviewsColumn
import com.example.blanball.presentation.views.widgets.loaders.Loader
import com.example.blanball.utils.makeCall
import com.example.blanball.utils.writeEmail

@Composable
fun PublicProfileScreen(
    state: UiState,
    onInviteToAnEventClicked: () -> Unit,
) {
    val currentState: PublicProfileMainContract.State =
        (state as? PublicProfileMainContract.State) ?: PublicProfileMainContract.State(
            PublicProfileMainContract.ScreenViewState.Loading
        )
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter,
    ) {
        (state as? PublicProfileMainContract.State)?.let {
            Image(
                painter = painterResource(id = R.drawable.public_profile_cover),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
                ) {
                    Column(
                        Modifier.padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 20.dp
                        )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(state.userAvatar.value),
                            contentDescription = null,
                            modifier = Modifier
                                .size(144.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = state.userLastNameText.value, style = typography.h2, fontSize = 20.sp,
                                color = primaryDark
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = state.userFirstNameText.value,
                                style = typography.h2, fontSize = 20.sp,
                                color = primaryDark,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                            )
                            Text(
                                text = state.userRoleText.value,
                                style = typography.h5,
                                color = secondaryNavy,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.End,
                            )
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(id = R.string.qualification),
                                style = typography.h6, //TODO(color)
                                color = annotationGray,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Start,
                            )
                            Box(
                                modifier = Modifier
                                    .background(color = backgroundItems, shape = shapes.small)
                                    .wrapContentWidth()
                            ) {
                                Text(
                                    text = if (state.userIsVerified.value)
                                        stringResource(id = R.string.verified)
                                    else stringResource(id = R.string.unverified),
                                    style = typography.h6,
                                    color = primaryDark,
                                    textAlign = TextAlign.Start,
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                            }
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Button(
                            onClick = { onInviteToAnEventClicked },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
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
                        Row() {
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
                                shape = shapes.medium,
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
                            Spacer(modifier = Modifier.size(10.dp))
                            Button(
                                onClick = { makeCall(state.userPhoneNumberText.value, context) },
                                modifier = Modifier
                                    .height(45.dp)
                                    .weight(1f)
                                    .border(
                                        width = 1.dp,
                                        color = defaultLightGray
                                    ),
                                shape = shapes.medium,
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
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = stringResource(id = R.string.about_youself),
                            style = typography.h5,
                            color = annotationGray
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = state.aboutUserText.value,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            style = typography.h5,
                            color = primaryDark
                        )
                    }
                }
                Card(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
                ) {
                    Column(
                        Modifier.padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 20.dp
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.game_stats),
                            style = typography.h2,
                            fontSize = 16.sp,
                            color = primaryDark
                        )
                        Spacer(modifier = Modifier.size(28.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color = bgLight, shape = shapes.medium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_flag),
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.game_position),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                Text(text = state.userPositionText.value, style = typography.h5, color = primaryDark)
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color = bgLight, shape = shapes.medium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_dumbbell),
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.weight),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                val userWeight = state.userWeightText.value.toString()
                                val weightSuffix = stringResource(id = R.string.weight_meas_units)
                                Text(
                                    text = "$userWeight $weightSuffix",
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
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color = bgLight, shape = shapes.medium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_leg),
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.kicking_leg),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                Text(
                                    text = state.userWorkingLegText.value,
                                    style = typography.h5,
                                    color = primaryDark
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(color = bgLight, shape = shapes.medium)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_ruler),
                                    contentDescription = null,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.height),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                val userHeight = state.userHeightText.value.toString()
                                val heightSuffix = stringResource(id = R.string.height_meas_units)
                                Text(
                                    text = "$userHeight $heightSuffix",
                                    style = typography.h5,
                                    color = primaryDark
                                )
                            }
                        }
                    }
                }
                Card(
                    Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(top = 20.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
                ) {
                    Column(
                        Modifier.padding(
                            top = 16.dp,
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 20.dp
                        )
                    ) {
                        Row() {
                            Text(
                                text = stringResource(id = R.string.ratings_and_reviews),
                                style = typography.h2,
                                color = primaryDark,
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Box(
                                modifier = Modifier.weight(1f),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Row() {
                                    Text(
                                        text = state.rating.value.toString(),
                                        style = typography.subtitle2,
                                        fontSize = 22.sp,
                                        color = primaryDark,
                                    )
                                    Text(
                                        text = stringResource(id = R.string.five_scores),
                                        style = typography.h5,
                                        color = DarkOverlay
                                    )
                                    Spacer(modifier = Modifier.size(4.dp))
                                    Icon(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .align(CenterVertically),
                                        painter = painterResource(id = R.drawable.empty_star),
                                        tint = orangeStarColor,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                        val gradesCount = state.gradesCount.value.toString()
                        val gradesText = stringResource(id = R.string.grades)
                        Text(
                            text = "$gradesCount $gradesText",
                            style = typography.h6,
                            color = selectedDarkGray
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        DisplayUserReviewsColumn(it)
                    }
                }
            }
        }
    }
    if (currentState.state is PublicProfileMainContract.ScreenViewState.Loading) {
        Loader()
    }
}