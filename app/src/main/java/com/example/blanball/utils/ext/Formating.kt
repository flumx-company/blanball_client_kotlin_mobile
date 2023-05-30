package com.example.blanball.utils.ext

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal fun String.formatDateReview(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM", Locale("uk", "UA"))
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

internal fun String.formatDatePlannedEvents(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM", Locale("uk", "UA"))
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

internal fun Any?.formatRating(): String {
    if (this == null) {
        return "0.0"
    }
    val decimalFormat = DecimalFormat("0.0")
    return decimalFormat.format(this)
}

internal fun Any.formatRatingToFloat(): Float? {
    return when (this) {
        is Double -> this.toFloat()
        else -> null
    }
}

internal fun String.formatDatePlannedEventsToTime(duration: Int = 0): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("HH:mm", Locale("uk", "UA"))
    val calendar = Calendar.getInstance()
    val date = inputFormat.parse(this)
    calendar.time = date!!
    calendar.add(Calendar.HOUR_OF_DAY, duration)
    return outputFormat.format(calendar.time)
}
