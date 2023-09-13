package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.backgroundItems
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun PreviewOfTheEventPosterButton(
    openPreviewClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openPreviewClicked() }
            .background(color = backgroundItems, shape = shapes.medium)
            .border(width = 1.dp, color = defaultLightGray, shape = shapes.medium)
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_img) ,
                contentDescription = null,
                tint = secondaryNavy

            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.event_preview),
                    fontSize = 13.sp,
                    lineHeight = 20.sp,
                    style = typography.h4,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF262541),
                    textAlign = TextAlign.Center,
                )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_2),
                contentDescription = null,
                tint = primaryDark
            )
        }
    }
}