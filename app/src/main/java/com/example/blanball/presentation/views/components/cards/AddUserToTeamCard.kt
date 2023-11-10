package com.example.blanball.presentation.views.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.blanball.presentation.theme.avatarGrey
import com.example.blanball.presentation.theme.backgroundClassicRed
import com.example.blanball.presentation.theme.classicRed
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun AddUserToTeam(
    clickCallback: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = shapes.medium)
            .border(width = 1.dp, color = classicRed, shape = shapes.medium)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { clickCallback?.let { it() } },
        contentAlignment = Alignment.Center
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(color = avatarGrey, shape = RoundedCornerShape(size = 100.dp))
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = stringResource(R.string.add_to_team),
                fontSize = 13.sp,
                lineHeight = 20.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = Color(0xFF575775),
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(
                        color = backgroundClassicRed,
                        shape = RoundedCornerShape(size = 100.dp)
                    )
                    .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    tint = classicRed,
                )
            }
        }
    }
}