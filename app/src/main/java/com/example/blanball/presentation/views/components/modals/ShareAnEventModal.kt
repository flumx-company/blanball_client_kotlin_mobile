package com.example.blanball.presentation.views.components.modals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.mainGreen
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.textinputs.ReadOnlyOutlinePlaceholder
import com.example.domain.utils.Endpoints

@Composable
fun ShareAnEventModal(
    copyLinkBtnClicked: () -> Unit,
    backBtnClicked: () -> Unit,
    currentEventId: Int,
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    val eventUrl = Endpoints.WEB_DOMAIN + Endpoints.DOMAIN_EVENTS_PATH + currentEventId
    val configuration = LocalConfiguration.current

    AlertDialog(
        modifier = Modifier
            .widthIn(max = configuration.screenWidthDp.dp - 20.dp)
            .wrapContentHeight(),
        onDismissRequest = {},
        buttons = {},
        text = {
            Column {
                Spacer(modifier = Modifier.size(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.share_an_event),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        style = typography.h3,
                        fontWeight = FontWeight(700),
                        color = primaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        modifier = Modifier.clickable {
                            backBtnClicked()
                        },
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                        tint = primaryDark
                    )
                }
                Spacer(modifier = Modifier.size(12.dp))
                ReadOnlyOutlinePlaceholder(
                    modifier = Modifier.fillMaxWidth(),
                    value = eventUrl,
                    onValueChange = {},
                    trailingIconResId = R.drawable.ic_copy,
                    labelResId = R.string.link_on_event,
                )
                Spacer(modifier = Modifier.size(10.dp))
                Button(
                    onClick = copyLinkBtnClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clickable {
                            clipboardManager.setText(AnnotatedString(text = eventUrl))
                        },
                    shape = shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = mainGreen,
                        contentColor = Color.White,
                    ),
                ) {
                    Text(
                        text = stringResource(R.string.copy_link),
                        style = typography.h4,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                    )
                }
            }
        }
    )
}