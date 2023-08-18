package com.example.blanball.presentation.views.screens.versions

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.BuildConfig
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.bgLight2
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.selectedDarkGray
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.theme.waterLightBlue

@Composable
fun VersionsScreen(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Тут буде написано, що саме ми додали до наявного функціоналу",// TODO()
                fontSize = 16.sp,
                lineHeight = 20.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
            )
            Spacer(modifier = Modifier.size(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(3) {
                    Image(
                        modifier = Modifier
                            .width(104.dp)
                            .height(118.dp)
                            .clip(shapes.small),
                        painter = painterResource(id = R.drawable.bg_training_user_step_2), //TODO
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Box(
                modifier = Modifier
                    .background(color = bgLight, shape = RoundedCornerShape(size = 8.dp))
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 16.dp)
            ) {
                Column {
                    Text(
                        text = (stringResource(R.string.about_new_on_version) + " ${BuildConfig.VERSION_NAME}"), //TODO
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF262541),
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Row  {
                        Box(modifier = Modifier.padding(start = 3.dp, top = 7.dp, end = 3.dp, bottom = 7.dp)) {
                            Icon(
                                modifier = Modifier
                                    .size(6.dp),
                                painter = painterResource(id = R.drawable.ic_dot),
                                contentDescription = null,
                                tint = selectedDarkGray,
                            )
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            modifier = Modifier
                                .width(292.dp)
                                .height(88.dp),
                            text = "Maecenas dignissim justo eget nulla rutrum molestie. Maecenas lobortis sem dui, vel rutrum risus tincidunt ullamcorper. Proin eu enim metus.", // TODO()
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Більш детально про оновлення",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.major),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(600),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Divider(color = defaultLightGray)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row {
                Box(modifier = Modifier.padding(start = 3.dp, top = 7.dp, end = 3.dp, bottom = 7.dp)) {
                Icon(
                    modifier = Modifier
                        .size(6.dp),
                    painter = painterResource(id = R.drawable.ic_dot),
                    contentDescription = null,
                    tint = selectedDarkGray,
                )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier
                        .width(292.dp)
                        .height(88.dp),
                    text = "Maecenas dignissim justo eget nulla rutrum molestie. Maecenas lobortis sem dui, vel rutrum risus tincidunt ullamcorper. Proin eu enim metus.", // TODO()
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.minor),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(600),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Divider(color = defaultLightGray)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row {
                Box(modifier = Modifier.padding(start = 3.dp, top = 7.dp, end = 3.dp, bottom = 7.dp)) {
                Icon(
                    modifier = Modifier
                        .size(6.dp),
                    painter = painterResource(id = R.drawable.ic_dot),
                    contentDescription = null,
                    tint = selectedDarkGray,
                )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier
                        .width(292.dp)
                        .height(88.dp),
                    text = "Maecenas dignissim justo eget nulla rutrum molestie. Maecenas lobortis sem dui, vel rutrum risus tincidunt ullamcorper. Proin eu enim metus.", // TODO()
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.patch),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(600),
                    color = primaryDark,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Divider(color = defaultLightGray)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Row {
                Box(modifier = Modifier.padding(start = 3.dp, top = 7.dp, end = 3.dp, bottom = 7.dp)) {
                Icon(
                    modifier = Modifier
                        .size(6.dp),
                    painter = painterResource(id = R.drawable.ic_dot),
                    contentDescription = null,
                    tint = selectedDarkGray,
                )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    modifier = Modifier
                        .width(292.dp)
                        .height(88.dp),
                    text = "Maecenas dignissim justo eget nulla rutrum molestie. Maecenas lobortis sem dui, vel rutrum risus tincidunt ullamcorper. Proin eu enim metus.", // TODO()
                    fontSize = 14.sp,
                    lineHeight = 22.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(R.string.history_of_updates),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(600),
                color = primaryDark,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = bgLight2, shape = shapes.medium)
                    .clickable { } //TODO()
                    .padding(16.dp)
            ) {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Version 0.0.1", //TODO()
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(500),
                            color = primaryDark,
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Box(
                            modifier = Modifier
                                .background(color = waterLightBlue, shape = shapes.small)
                                .padding(start = 8.dp, end = 8.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.new_version),
                                fontSize = 12.sp,
                                lineHeight = 20.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(500),
                                color = mainGreen,
                                textAlign = TextAlign.Center,
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "14.10.2023",  //TODO()
                            fontSize = 12.sp,
                            lineHeight = 20.sp,
                            style = typography.h4,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                        )
                        Spacer(modifier = Modifier.size(12.dp))
                        Icon(
                            modifier = Modifier.clickable {  }, //TODO()
                            painter = painterResource(id = R.drawable.ic_arrow_2),
                            contentDescription = null,
                            tint = primaryDark
                        )
                    }
            }
        }
    }
}