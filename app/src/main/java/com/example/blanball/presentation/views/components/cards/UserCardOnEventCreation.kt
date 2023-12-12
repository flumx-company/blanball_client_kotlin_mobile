package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.typography

@Composable
fun UserCardOnEventCreation(
    userAvatarUrl: String,
    userFirstName: String,
    userLastName: String,
    userId: Int,
    onUserSelected: (userId: Int) -> Unit
) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, bottom = 4.dp, start = 12.dp, end = 12.dp)
                .clickable {
                    onUserSelected(userId)
                } ,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.size(36.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (userAvatarUrl.isNullOrEmpty()) {
                        Box(
                            modifier = Modifier.size(36.dp),
                            contentAlignment = Alignment.CenterStart,
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.circle_avatar),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(shape = RoundedCornerShape(4.dp))
                            )
                            Text(
                                text = "${userFirstName.firstOrNull() ?: ""}${userLastName.firstOrNull() ?: ""}",
                                modifier = Modifier.align(
                                    Alignment.Center
                                ),
                                style = typography.h2, fontSize = 16.sp, color = mainGreen
                            )
                        }
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(userAvatarUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "$userFirstName $userLastName",
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = primaryDark,
            )
        }
    }