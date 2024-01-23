package com.example.blanball.presentation.views.components.modals

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.typography

@Composable
fun SuccessEditMyProfileModal(
) {
    val configuration = LocalConfiguration.current
    AlertDialog(
        modifier = Modifier
            .wrapContentHeight()
            .widthIn(max = configuration.screenWidthDp.dp - 20.dp),
        text = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier
                            .width(60.dp)
                            .height(68.dp),
                        painter = painterResource(id = R.drawable.profile_edit_ic),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Column {
                        Text(
                            text = stringResource(R.string.save_1),
                            style = typography.h3,
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            color = primaryDark,
                            fontWeight = FontWeight(700),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = stringResource(R.string.info_saved),
                            style = typography.h4,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(400),
                            color = secondaryNavy,
                            textAlign = TextAlign.Start,
                        )
                    }
                }
            }
        },
        onDismissRequest = {},
        buttons = {},
    )
}