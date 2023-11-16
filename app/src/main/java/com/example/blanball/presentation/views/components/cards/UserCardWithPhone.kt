package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
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
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@Composable
fun UserCardWithPhone(
    userAvatarUrl: String,
    firstName: String,
    lastName: String,
    userPhone: String,
    userRole: String,
    clickCallback: (() -> Unit)? = null
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { clickCallback?.let { it() } }) {
        Divider(color = defaultLightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.size(12.dp))
        Row {
            if (userAvatarUrl.isNullOrEmpty()) {
                Box(
                    modifier = Modifier.size(44.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.circle_avatar),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .fillMaxSize()
                    )
                    Text(
                        text = "${firstName.firstOrNull() ?: ""}${lastName.firstOrNull() ?: ""}",// TODO()
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
                        .size(44.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = "$firstName $lastName",
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = primaryDark,
                )
                Text(
                    text = userPhone,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                    color = secondaryNavy,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = userRole,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = secondaryNavy,
            )
        }
    }
}