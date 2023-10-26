package com.example.blanball.presentation.views.components.drawers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.BuildConfig
import com.example.blanball.R
import com.example.blanball.presentation.data.NavigationDrawerMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.NavigationDrawerFooterBanner
import com.example.blanball.presentation.views.components.buttons.FoundAnErrorButton

@Composable
fun NavigationDrawer(
    state: UiState,
    onFriendsScreenClicked: () -> Unit,
    onPlannedEventsScreenClicked: () -> Unit,
    onNotificationsScreenClicked: () -> Unit,
    onSettingsScreenClicked: () -> Unit,
    onMyProfileScreenClicked: () -> Unit,
    onVersionsScreenClicked: () -> Unit,
    onLogOutClicked: () -> Unit,
    onFoundAnErrorClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        (state as? NavigationDrawerMainContract.State)?.let {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(id = R.string.blanball),
                        style = typography.h3,
                        lineHeight = 32.sp,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(800),
                        color = primaryDark
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Text(
                        modifier = Modifier.clickable(onClick = onVersionsScreenClicked),
                        text = stringResource(id = R.string.blanball_version) + " ${BuildConfig.VERSION_NAME}",
                        style = typography.h4,
                        fontSize = 10.sp,
                        lineHeight = 16.sp,
                        color = secondaryNavy,
                        fontWeight = FontWeight(400),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.clickable(onClick = onLogOutClicked),
                        painter = painterResource(id = R.drawable.ic_log_out),
                        tint = primaryDark,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                Box(
                    modifier = Modifier
                        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(start = 12.dp, top = 8.dp, end = 12.dp, bottom = 8.dp)
                        .height(64.dp)
                        .fillMaxWidth()
                        .clickable(onClick = onMyProfileScreenClicked),
                    contentAlignment = Alignment.CenterStart,

                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                            if (state.userAvatar.value.isNullOrEmpty()) {
                                Box(
                                    modifier = Modifier.size(48.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.circle_avatar),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(48.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop,
                                        alignment = Alignment.Center
                                    )
                                    Text(
                                        text = "${state.userLastNameText.value.firstOrNull()} ${state.userFirstNameText.value.firstOrNull()}",
                                        style = typography.h2, fontSize = 22.sp, color = mainGreen
                                    )
                                }
                            } else {
                                Image(
                                    painter = rememberAsyncImagePainter(state.userAvatar.value),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.Center
                                )
                            }
                        Spacer(modifier = Modifier.size(12.dp))
                        Column() {
                            Text(
                                text = "${state.userFirstNameText.value} ${state.userLastNameText.value}",
                                fontSize = 16.sp,
                                lineHeight = 24.sp,
                                fontWeight = FontWeight(800),
                                color = primaryDark,
                            )
                            Text(
                                text = stringResource(id = R.string.my_profile),
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                fontWeight = FontWeight(500),
                                color = mainGreen
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(1f)
                            .clickable {
                                onFriendsScreenClicked()
                            }
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(topStart = 8.dp)
                            ),
                         contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_peoples),
                                contentDescription = null,
                                tint = primaryDark
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.friends),
                                fontSize = 11.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                                color = primaryDark,
                                style = typography.h4
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(1f)
                            .clickable { onPlannedEventsScreenClicked() }
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(topEnd = 8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_calendar),
                                contentDescription = null,
                                tint = primaryDark
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.planned_events_side_bar),
                                fontSize = 11.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                                color = primaryDark,
                                style = typography.h4
                            )
                        }
                    }
                }
                Row {
                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(1f)
                            .clickable { onNotificationsScreenClicked() }
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(bottomStart = 8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_bell),
                                contentDescription = null,
                                tint = primaryDark
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.notifications),
                                fontSize = 11.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                                color = primaryDark,
                                style = typography.h4
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(56.dp)
                            .weight(1f)
                            .clickable { onSettingsScreenClicked() }
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(bottomEnd = 8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_settings),
                                contentDescription = null,
                                tint = primaryDark
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.params),
                                fontSize = 11.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight(500),
                                color = primaryDark,
                                style = typography.h4
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FoundAnErrorButton(
                        buttonClickCallback = { onFoundAnErrorClicked() }
                    )
                }
                Spacer(modifier = Modifier.size(20.dp))
                NavigationDrawerFooterBanner()
            }
        }
    }
}