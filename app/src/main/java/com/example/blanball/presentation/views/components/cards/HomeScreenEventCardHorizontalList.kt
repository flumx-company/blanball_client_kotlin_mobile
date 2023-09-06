package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgItemsGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.texts.TextBadge

@Composable
fun HomeScreenEventCardHorizontalList(
    count: Int,
    clickToEventCardCallback: () -> Unit,
){
    LazyRow {
        items(count = count) {
            DefaultCardWithColumn (
                clickCallback = clickToEventCardCallback
            ) {
                Row {
                    Box(
                        Modifier
                            .width(48.dp)
                            .height(48.dp)
                            .background(
                                color = bgItemsGray,
                                shape = shapes.small
                            )
                    ) {
                        Image(
                            modifier = Modifier
                                .clip(shapes.small)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.ic_hands_emoji),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Column {
                        Text(
                            text = stringResource(id = R.string.friendly_match),
                            fontSize = 16.sp,
                            lineHeight = 20.sp,
                            style = typography.h3,
                            fontWeight = FontWeight(700),
                            color = primaryDark,
                        )
                        Spacer(modifier = Modifier.size(6.dp))
                        Row {
                            Text(
                                text = "16 червня", //TODO()
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(500),
                                color = primaryDark,
                            )
                            Spacer(modifier = Modifier.size(12.dp))
                            Text(
                                text = "12:00 - 14:00",
                                fontSize = 13.sp,
                                lineHeight = 20.sp,
                                style = typography.h4,
                                fontWeight = FontWeight(500),
                                color = secondaryNavy,
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(12.dp))
                Row {
                    Icon(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.ic_location),
                        tint = mainGreen,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "Запоріжжя, Центральна, стадіон «Торпеда»", //TODO()
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = mainGreen,
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                    Row {
                        TextBadge(textResId = R.string.football)
                        Spacer(modifier = Modifier.size(4.dp))
                        TextBadge(textResId = R.string.mans)
                        Spacer(modifier = Modifier.size(4.dp))
                        TextBadge(textResId = R.string.withour_divison)
                    }
            }
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}