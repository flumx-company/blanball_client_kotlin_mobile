package com.example.blanball.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

internal fun makeCall(number: String, context: Context) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel:$number")
    context.startActivity(intent)
}

internal fun writeEmail(
    addresses: Array<String>,
    context: Context,
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, addresses)
    }
    context.startActivity(intent)
}