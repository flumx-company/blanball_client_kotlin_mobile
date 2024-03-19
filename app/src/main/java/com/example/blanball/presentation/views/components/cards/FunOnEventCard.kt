package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.ratingbars.RatingBarWithNum
import com.example.domain.entity.responses.GetEventByIdResponseCurrentFanEntity

@Composable
fun FanOnEventCard(
    clickCallback: (userId: Int) -> Unit?,
    invitedFansList: List<GetEventByIdResponseCurrentFanEntity>
) {
        Column {
            invitedFansList.forEachIndexed { index, fan ->
                FanItem(
                    funItem = fan,
                    clickCallback = {clickCallback(fan.id)}
                )
                if (index < invitedFansList.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

@Composable
fun FanItem(
    funItem: GetEventByIdResponseCurrentFanEntity,
    clickCallback: (() -> Unit)?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White, shape = shapes.medium)
            .border(width = 1.dp, color = mainGreen, shape = shapes.medium)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { clickCallback?.let { it() } },
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (funItem.profile.avatar_url.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.size(36.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.circle_avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                    )
                    Text(
                        text = "${funItem.profile.name.firstOrNull() ?: ""}${funItem.profile.last_name.firstOrNull() ?: ""}",
                        modifier = Modifier.align(
                            Alignment.Center
                        ),
                        style = typography.h2, fontSize = 16.sp, color = mainGreen
                    )
                }
            } else {
                Image(
                    painter = rememberAsyncImagePainter(funItem.profile.avatar_url),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text(
                    text = "${funItem.profile.name} ${funItem.profile.last_name}",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = primaryDark,
                )
                Text(
                    text = funItem.profile.position ?: "--",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            RatingBarWithNum(ratingValue = 5)
        }
    }
}