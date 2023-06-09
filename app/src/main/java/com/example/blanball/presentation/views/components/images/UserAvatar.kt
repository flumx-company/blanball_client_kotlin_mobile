package com.example.blanball.presentation.views.components.images

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.blanball.R
import com.example.blanball.presentation.data.StartScreensMainContract
import com.example.blanball.presentation.data.UiState
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.typography

//@Composable
//fun UserAvatar(
//    state: UiState
//) {
//    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
//        if (state.userAvatar.value.isNullOrEmpty()) {
//            Box(
//                modifier = Modifier.size(112.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.circle_avatar),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .fillMaxSize()
//                )
//                Text(
//                    text = "${state.userLastNameText.value.firstOrNull() ?: ""} ${state.userFirstNameText.value.firstOrNull() ?: ""}",
//                    modifier = Modifier.align(
//                        Alignment.Center
//                    ),
//                    style = typography.h2, fontSize = 56.sp, color = mainGreen
//                )
//            }
//        } else {
//            Image(
//                painter = rememberAsyncImagePainter(state.userAvatar.value),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(144.dp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop
//            )
//        }
//}