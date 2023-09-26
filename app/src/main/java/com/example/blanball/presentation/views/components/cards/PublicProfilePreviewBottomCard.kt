package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.backgroundLight
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shadowDark
import com.example.blanball.presentation.theme.typography

@Composable
fun PublicProfilePreviewBottomCard(
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 10.dp,
                spotColor = shadowDark,
                ambientColor = shadowDark,
            )
            .fillMaxWidth()
            .height(84.dp)
            .background(
                color = backgroundLight,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )
            )
            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        )
        {
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {  }
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(size = 6.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    ) {
                    Text(
                        text = stringResource(R.string.for_editing),
                        fontSize = 13.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF424257),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_return_left),
                        contentDescription = null,
                        tint = primaryDark
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(28.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .clickable {  }
                    .background(
                        color = primaryDark,
                        shape = RoundedCornerShape(size = 6.dp)
                    )
                    .padding(start = 8.dp, top = 2.dp, end = 8.dp, bottom = 2.dp),
                contentAlignment = Alignment.Center
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.sav_and_exit),
                        fontSize = 13.sp,
                        lineHeight = 24.sp,
                        style = typography.h4,
                        fontWeight = FontWeight(500),
                        color = Color.White,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    }
}