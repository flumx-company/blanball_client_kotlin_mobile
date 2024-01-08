package com.example.blanball.presentation.views.screens.myprofile

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.MyProfileScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.DarkOverlay
import com.example.blanball.presentation.theme.accentLightGreen
import com.example.blanball.presentation.theme.annotationGray
import com.example.blanball.presentation.theme.bgItemsGray
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.semiTransparentBlack
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.successValidationGreen
import com.example.blanball.presentation.theme.successValidationGreenBG
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.AttentionText
import com.example.blanball.presentation.views.components.boxes.IcBox
import com.example.blanball.presentation.views.components.cards.DefaultCardWithColumn
import com.example.blanball.presentation.views.components.loaders.Loader
import com.example.blanball.utils.ext.formatRating

@Composable
fun MyProfilePreviewScreen(
    state: UiState,
    paddingValues: PaddingValues,
    onBackClicked: () -> Unit,
    onSaveClicked: () -> Unit,
) {
    val currentState: MyProfileScreensMainContract.State =
        (state as? MyProfileScreensMainContract.State) ?: MyProfileScreensMainContract.State(
            MyProfileScreensMainContract.ScreenViewState.Idle
        )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(color = semiTransparentBlack),
            contentAlignment = Alignment.Center
        ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.size(10.dp))
                Box(
                    modifier = Modifier
                        .height(28.dp)
                        .wrapContentWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row  (
                        verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .height(28.dp)
                                .wrapContentWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .clickable { onBackClicked() },
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.continue_edit),
                                    fontSize = 13.sp,
                                    lineHeight = 24.sp,
                                    style = typography.h4,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFE2E2E9),
                                    textAlign = TextAlign.Center,
                                )
                                Spacer(modifier = Modifier.size(6.dp))
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    painter = painterResource(id = R.drawable.ic_backarrow),
                                    contentDescription = null,
                                    tint = bgItemsGray,
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .height(28.dp)
                        .wrapContentWidth()
                        .background(
                            color = annotationGray,
                            shape = RoundedCornerShape(size = 6.dp)
                        )
                        .clickable { onSaveClicked() },
                    contentAlignment = Alignment.Center
                ) {
                    Row (
                        modifier = Modifier.padding(horizontal = 6.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(R.string.save),
                            fontSize = 13.sp,
                            lineHeight = 24.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(500),
                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = null,
                            tint = bgItemsGray,
                        )
                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
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
            (state as? MyProfileScreensMainContract.State)?.let {
                Column(
                    modifier = Modifier.padding(
                        start = 20.dp, top = 20.dp, end = 20.dp, bottom = 0.dp,
                    )
                ) {
                    DefaultCardWithColumn(
                        columnPadStart = 0.dp,
                        columnPadEnd = 0.dp,
                        columnPadTop = 0.dp
                    ) {
                        Box(
                            Modifier
                                .background(color = accentLightGreen)
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Spacer(modifier = Modifier.size(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    text = stringResource(id = R.string.verified),
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
                            if (state.myAvatarUrl.value.isNullOrEmpty()) {
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
                                        text = "${state.myFirstNameText.value.firstOrNull() ?: ""}${state.myLastNameText.value.firstOrNull() ?: ""}",
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
                                        .size(144.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.size(12.dp))
                            Column(
                                horizontalAlignment = Alignment.Start,
                                modifier = Modifier.height(144.dp)
                            ) {
                                Text(
                                    text = state.myFirstNameText.value,
                                    style = typography.h2,
                                    fontSize = 20.sp,
                                    color = primaryDark
                                )
                                Text(
                                    text = state.myLastNameText.value,
                                    style = typography.h2, fontSize = 20.sp,
                                    color = primaryDark,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.wrapContentWidth()
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Text(
                                    text = "${state.roleState.value} / ${state.positionState.value}",
                                    style = typography.h6,
                                    color = primaryDark,
                                    modifier = Modifier
                                        .wrapContentWidth()
                                )
                                Spacer(modifier = Modifier.size(8.dp))
                                Row {
                                    Text(
                                        text = state.ratingState.value.formatRating(),
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
                            onClick = {},
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
                        if (state.emailStringState.value.isNotEmpty() || state.phoneText.value.isNotEmpty()) {
                            Row(
                                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                if (state.emailRadioButtonState.value) {
                                    Button(
                                        onClick = {},
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
                                if (state.phoneNumberRadioButtonState.value) {
                                    Button(
                                        onClick = {},
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
                                stringResource(id = R.string.сonfirmed),
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
                            text = state.aboutMeText.value!!,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = 16.dp, end = 16.dp),
                            style = typography.h5,
                            color = primaryDark,
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
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
                            IcBox(
                                icon = R.drawable.ic_flag, modifier = Modifier
                                    .background(bgLight, shape = shapes.medium)
                                    .size(40.dp)
                            )
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.game_position),
                                    style = typography.h6,
                                    color = DarkOverlay,
                                )
                                Text(
                                    text = state.positionState.value!!,
                                    style = typography.h5,
                                    color = primaryDark
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IcBox(
                                icon = R.drawable.ic_dumbbell, modifier = Modifier
                                    .background(bgLight, shape = shapes.medium)
                                    .size(40.dp)
                            )
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.weight),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                Text(
                                    text = " ${state.weightState.value} ${stringResource(id = R.string.weight_meas_units)}",
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
                            IcBox(
                                icon = R.drawable.ic_leg, modifier = Modifier
                                    .background(bgLight, shape = shapes.medium)
                                    .size(40.dp)
                            )
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.kicking_leg),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                Text(
                                    text = state.workingLegState.value!!,
                                    style = typography.h5,
                                    color = primaryDark
                                )
                            }
                            Spacer(modifier = Modifier.weight(1f))
                            IcBox(
                                icon = R.drawable.ic_ruler, modifier = Modifier
                                    .background(bgLight, shape = shapes.medium)
                                    .size(40.dp)
                            )
                            Column(Modifier.weight(1f)) {
                                Text(
                                    text = stringResource(id = R.string.height),
                                    style = typography.h6,
                                    color = DarkOverlay
                                )
                                Text(
                                    text = "${state.heightState.value} ${stringResource(id = R.string.height_meas_units)}",
                                    style = typography.h5,
                                    color = primaryDark
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    DefaultCardWithColumn {
                        Text(
                            text = stringResource(id = R.string.ratings_and_reviews),
                            style = typography.h2,
                            color = primaryDark,
                            fontSize = 16.sp,
                        )
                        Spacer(modifier = Modifier.size(12.dp))

                        AttentionText(
                            text = "${state.myFirstNameText.value} ${state.myLastNameText.value} ${
                                stringResource(
                                    id = R.string.have_not_yet_received_reviews
                                )
                            }"
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    DefaultCardWithColumn {
                        Text(
                            text = stringResource(id = R.string.planned_submissions),
                            style = typography.h2,
                            color = primaryDark,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        AttentionText(
                            text = "${state.myLastNameText.value} ${state.myFirstNameText.value} ${
                                stringResource(
                                    id = R.string.have_not_yet_received_planned_events
                                )
                            }"
                        )
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
            }
        }
        if (currentState.state is MyProfileScreensMainContract.ScreenViewState.Loading) {
            Loader(backgroundColor = Color.White, textColor = primaryDark)
        }
    }
}