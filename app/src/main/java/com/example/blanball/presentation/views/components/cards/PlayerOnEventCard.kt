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
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.blanball.presentation.views.components.banners.NoHaveContentBanner
import com.example.blanball.presentation.views.components.ratingbars.RatingBarWithNum
import com.example.domain.entity.responses.GetEventByIdResponsePlayerProfileXEntity

@Composable
fun PlayerOnEventCard(
    clickCallback: (() -> Unit)? = null,
    invitedPlayersList: List<GetEventByIdResponsePlayerProfileXEntity>

) {
    if (invitedPlayersList.isEmpty()) {
        NoHaveContentBanner(
            headerTextId = R.string.not_found_users_for_this_filter,
            secTextId = R.string.change_search_params,
        )
    } else {
        for (player in invitedPlayersList) {
            Column {
                PlayerItem(player = player, clickCallback = {})
            }
        }
    }
}


@Composable
fun PlayerItem(
    player: GetEventByIdResponsePlayerProfileXEntity,
    clickCallback: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White, shape = shapes.medium)
            .border(width = 1.dp, color = mainGreen, shape = shapes.medium)
            .padding(vertical = 24.dp, horizontal = 12.dp)
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
                if (player.avatar_url.isNullOrEmpty()) {
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
                            text = "${player.name.firstOrNull() ?: ""}${player.last_name.firstOrNull() ?: ""}",
                            modifier = Modifier.align(
                                Alignment.Center
                            ),
                            style = typography.h2, fontSize = 16.sp, color = mainGreen
                        )
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(player.avatar_url),
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
                        text = "${player.name} ${player.last_name}",
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = primaryDark,
                    )
                    Text(
                        text = player.position ?: "--",
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
}