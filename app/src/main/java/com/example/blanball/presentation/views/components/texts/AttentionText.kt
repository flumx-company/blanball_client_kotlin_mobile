package com.example.blanball.presentation.views.components.texts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.blanball.R
import com.example.blanball.presentation.theme.bgLight
import com.example.blanball.presentation.theme.primaryDark
import com.example.blanball.presentation.theme.secondaryNavy
import com.example.blanball.presentation.theme.shapes
import com.example.blanball.presentation.theme.typography

@Composable
fun AttentionText(
    text: String,
) {
    Box(modifier = Modifier.background(bgLight, shape = shapes.medium).fillMaxWidth().padding(8.dp)) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_attention),
                tint = secondaryNavy,
                contentDescription = null,
                modifier = Modifier.align(CenterVertically)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = text, style = typography.h5, color = primaryDark)
        }
    }
}