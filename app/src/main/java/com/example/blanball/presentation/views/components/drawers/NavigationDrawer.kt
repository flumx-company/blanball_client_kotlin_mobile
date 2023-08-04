package com.example.blanball.presentation.views.components.drawers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.BuildConfig
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.banners.NavigationDrawerFooterBanner
import com.example.blanball.presentation.views.components.buttons.FoundAnErrorButton

@Composable
fun NavigationDrawer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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
                    text = stringResource(id = R.string.blanball_version) + " ${BuildConfig.VERSION_NAME}",
                    style = typography.h4,
                    fontSize = 10.sp,
                    lineHeight = 16.sp,
                    color = secondaryNavy,
                    fontWeight = FontWeight(400),
                    textDecoration = TextDecoration.Underline,
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
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
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.circle_avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column() {
                        Text(
                            text = "Cтефанія Калиновська",
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
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(56.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(topStart = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = mainGreen,
                    ),
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
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(56.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(topEnd = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = mainGreen,
                    ),
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
            Row {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(56.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(bottomStart = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = mainGreen,
                    ),
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
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(56.dp)
                        .weight(1f),

                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = mainGreen,
                    ),
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
                        text = stringResource(id = R.string.settings),
                        fontSize = 11.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(500),
                        color = primaryDark,
                        style = typography.h4
                    )
                }
            }
           Spacer(modifier = Modifier.weight(1f))
            Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                FoundAnErrorButton()
            }
            Spacer(modifier = Modifier.size(20.dp))
            NavigationDrawerFooterBanner()
        }
        }
    }