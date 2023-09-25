package com.example.blanball.presentation.views.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.theme.waterLightBlue

@Composable
fun ChangePassButton(
    btnClicked: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = waterLightBlue, shape = shapes.medium)
            .clickable { btnClicked() }
            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(R.string.change_pass),
                fontSize = 15.sp,
                lineHeight = 24.sp,
                style = typography.h4,
                fontWeight = FontWeight(400),
                color = mainGreen,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = null,
                tint = mainGreen,
            )
        }
    }
}