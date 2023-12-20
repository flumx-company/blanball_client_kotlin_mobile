package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun EventBottomButtons(
    toJoinBtnClick: () -> Unit,
    shareBtnClick: () -> Unit,
    isMyEvent: Boolean ,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                color = bgLight,
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
    )
    {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = toJoinBtnClick,
                modifier = Modifier
                    .wrapContentWidth()
                    .height(40.dp),
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = mainGreen,
                    contentColor = Color.White,
                ),
            ) {
                if (isMyEvent) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings_2),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                }
                Text(
                    text = if (isMyEvent) stringResource(id = R.string.edit) else stringResource(R.string.to_join),
                    fontSize = 15.sp,
                    lineHeight = 24.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(400),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .width(148.dp)
                    .height(40.dp),
                border = BorderStroke(width = 1.dp, color = secondaryNavy),
                onClick = shareBtnClick,
                shape = shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = secondaryNavy,
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(
                        text = stringResource(R.string.share),
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                    )
                }
            }
        }
    }
}