package com.example.blanball.presentation.views.components.banners

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.blanball.R
import com.example.blanball.presentation.theme.defaultLightGray
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography
import com.example.blanball.utils.toFLumXUrlIntent
import com.example.blanball.utils.toPrivacyPolicyUrlIntent

@Composable
fun NavigationDrawerFooterBanner(){
    val context = LocalContext.current

    Divider(
        color = defaultLightGray,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(12.dp))
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
            modifier = Modifier.clickable {
                ContextCompat.startActivity(context, toPrivacyPolicyUrlIntent, null  )
            },
            text = stringResource(id = R.string.data_proto),
            lineHeight = 20.sp,
            fontSize = 12.sp,
            style = typography.h4,
            fontWeight = FontWeight(500),
            color = secondaryNavy,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.clickable {
                ContextCompat.startActivity(context, toFLumXUrlIntent, null  )
            },
            painter = painterResource(id = R.drawable.logo_flumx),
            tint = secondaryNavy,
            contentDescription = null
        )
        Text(
            modifier = Modifier.clickable {
                ContextCompat.startActivity(context, toFLumXUrlIntent, null  )
            },
            text = stringResource(id = R.string.flumx),
            lineHeight = 20.sp,
            fontSize = 12.sp,
            style = typography.h4,
            fontWeight = FontWeight(500),
            color = secondaryNavy,
        )
    }
}