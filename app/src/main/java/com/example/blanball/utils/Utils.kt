package com.example.blanball.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Locale

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

internal fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM", Locale("uk", "UA"))
    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}

internal fun formatRating(inputRating: Any?): String {
    val decimalFormat = DecimalFormat("0.0")
    return decimalFormat.format(inputRating)
}