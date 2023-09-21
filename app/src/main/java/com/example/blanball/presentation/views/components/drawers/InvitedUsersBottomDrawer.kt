package com.example.blanball.presentation.views.components.drawers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blanball.R
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography
import com.example.blanball.presentation.views.components.cards.InvitedUserCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InvitedUsersBottomDrawer(
    bottomDrawerState: SheetState,
    closeBottomDrawer: () -> Unit,
) {
    ModalBottomSheet(onDismissRequest = { closeBottomDrawer() }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        ) {
            Text(
                text = stringResource(R.string.invited_users),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                style = typography.h3,
                fontWeight = FontWeight(700),
                color = primaryDark,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.size(12.dp))
            repeat(5) {
                Box (
                    modifier = Modifier.background(color = Color.White, shape = shapes.small)
                ){
                    InvitedUserCard(
                        userAvatarUrl = "http://178.151.201.167:49291/blanball-media/users/MzQ_2023-06-27-10-01.jpg",
                        userFirstName = "Женя",
                        userLastName = "Жук"
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }

}