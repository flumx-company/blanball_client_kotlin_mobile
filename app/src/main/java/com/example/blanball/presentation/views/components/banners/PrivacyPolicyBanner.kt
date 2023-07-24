package com.example.blanball.presentation.views.components.banners

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.blanball.R
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.toPrivacyPolicyUrlIntent

@Composable
fun PrivacyPolicyBanner(){
    val context = LocalContext.current
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            text = stringResource(id = R.string.c_blanball),
            lineHeight = 20.sp,
            style = typography.h4,
            fontSize = 12.sp,
            fontWeight = FontWeight(500),
            color = secondaryNavy,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.policy_privacy),
            lineHeight = 20.sp,
            fontSize = 12.sp,
            style = typography.h4,
            fontWeight = FontWeight(500),
            color = secondaryNavy,
            modifier = Modifier.clickable {
                ContextCompat.startActivity(context, toPrivacyPolicyUrlIntent, null)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(id = R.drawable.logo_flumx),
            tint = secondaryNavy,
            contentDescription = null
        )
    }
}