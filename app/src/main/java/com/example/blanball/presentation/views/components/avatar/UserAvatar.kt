package com.example.blanball.presentation.views.components.avatar

//@Stable
//@Composable
//fun UserAvatar(
//    userAvatarUrl: String,
//){
//    if (user.profile.avatar_url.isNullOrEmpty()) {
//        Box(
//            modifier = Modifier.size(36.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.circle_avatar),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(RoundedCornerShape(6.dp))
//                    .size(36.dp)
//                    .clickable(onClick = { onClickedToPublicProfile(user.id) })
//            )
//            Text(
//                text = "${user.profile.last_name.firstOrNull() ?: ""}${user.profile.name.firstOrNull() ?: ""}",
//                modifier = Modifier.align(
//                    Alignment.Center
//                ),
//                style = typography.h2,
//                fontSize = 16.sp,
//                color = mainGreen
//            )
//        }
//    } else {
//        Image(
//            painter = rememberAsyncImagePainter(user.profile.avatar_url),
//            contentDescription = null,
//            modifier = Modifier
//                .clip(CircleShape)
//                .size(36.dp)
//                .clickable(onClick = { onClickedToPublicProfile(user.id) }),
//            contentScale = ContentScale.Crop
//        )
//    }
//}