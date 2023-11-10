package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@Composable
fun InvitedUserCard(
    userAvatarUrl: String,
    userFirstName: String,
    userLastName: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.size(36.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (userAvatarUrl.isNullOrEmpty()) {
                    Box(
                        modifier = Modifier.size(36.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.circle_avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = RoundedCornerShape(4.dp))
                        )
                        Text(
                            text = "${userFirstName.firstOrNull() ?: ""} ${userLastName.firstOrNull() ?: ""}",
                            modifier = Modifier.align(
                                Alignment.Center
                            ),
                            style = typography.h2, fontSize = 22.sp, color = mainGreen
                        )
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(userAvatarUrl),
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                            .clip(shape = RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "GK",
            fontSize = 14.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = secondaryNavy,
            )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Женя Жук",
            fontSize = 12.sp,
            lineHeight = 20.sp,
            style = typography.h4,
            fontWeight = FontWeight(500),
            color = primaryDark,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.ic_cancel),
            contentDescription = null,
            tint = secondaryNavy,
        )
    }
}