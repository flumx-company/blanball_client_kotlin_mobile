package com.example.blanball.presentation.views.components.banners

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun NoHaveContentBanner(
    headerTextId: Int,
    secTextId: Int,
    isBtnVisible: Boolean = false,
    onButtonClicked: () -> Unit = {},
    buttonTextId: Int = R.string.organize_event,
) {
    Box(modifier = Modifier
        .background(bgLight, shape = shapes.medium)
        .fillMaxWidth()
        .height(220.dp)
        .padding(20.dp)){
        Row (horizontalArrangement = Arrangement.SpaceBetween, ) {
            Column (modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = headerTextId),
                    style = typography.h3,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(800),
                    color = primaryDark
                )
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(id = secTextId),
                    style = typography.h4,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = secondaryNavy
                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(32.dp))
                   Row(
                       verticalAlignment = Alignment.CenterVertically,
                       ) {
                       if (isBtnVisible) {
                       Box(
                           modifier = Modifier
                               .height(40.dp)
                               .width(172.dp)
                               .shadow(
                                   elevation = 4.dp,
                                   shape = shapes.medium,
                                   clip = false,
                                   ambientColor = mainGreen,
                                   spotColor = mainGreen
                               )
                               .background(color = mainGreen, shape = shapes.medium)
                               .clickable { onButtonClicked() },
                           contentAlignment = Alignment.Center,
                       ) {
                           Text(
                               text = stringResource(id = buttonTextId),
                               style = typography.h4,
                               color = Color.White,
                               lineHeight = 24.sp,
                               fontWeight = FontWeight(500),
                           )
                       }
                       } else {
                           Spacer(modifier = Modifier.height(40.dp)
                               .width(172.dp))
                       }
                       Spacer(modifier = Modifier.weight(1f))
                       Image(
                           modifier = Modifier.size(49.dp),
                           painter = painterResource(id = R.drawable.ic_ball_dark),
                           contentDescription = null
                       )
                   }
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Image(
                modifier = Modifier
                    .width(60.dp)
                    .height(180.dp),
                painter = painterResource(id = R.drawable.no_have_content_banner_ic),
                contentScale = ContentScale.FillHeight,
                contentDescription = null
            )
        }
    }
}