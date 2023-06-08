package com.example.blanball.utils.ext

import com.example.domain.utils.Formats
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

internal fun String.formatDateReview(): String {
    val inputFormat = SimpleDateFormat(Formats.REVIEW_DATE_FORMAT_INPUT, Locale.getDefault())
    val outputFormat = SimpleDateFormat(Formats.REVIEW_DATE_FORMAT_OUTPUT, Locale("uk", "UA"))
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

internal fun String.formatDatePlannedEvents(): String {
    val inputFormat = SimpleDateFormat(Formats.EVENTS_DATE_FORMAT_INPUT, Locale.getDefault())
    val outputFormat = SimpleDateFormat(Formats.EVENTS_DATE_FORMAT_OUTPUT, Locale("uk", "UA"))
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}

internal fun Any?.formatRating(): String {
    if (this == null) {
        return Formats.DECIMAL_FORMAT
    }
    val decimalFormat = DecimalFormat(Formats.DECIMAL_FORMAT)
    return decimalFormat.format(this)
}

internal fun Any.formatRatingToFloat(): Float? {
    return when (this) {
        is Double -> this.toFloat()
        else -> null
    }
}

internal fun String.formatDatePlannedEventsToTime(duration: Int = 0): String {
    val inputFormat = SimpleDateFormat(Formats.EVENTS_DATE_FORMAT_INPUT, Locale.getDefault())
    val outputFormat =
        SimpleDateFormat(Formats.EVENTS_DATE_FORMAT_TO_TIME_OUTPUT, Locale("uk", "UA"))
    val calendar = Calendar.getInstance()
    val date = inputFormat.parse(this)
    calendar.time = date!!
    calendar.add(Calendar.HOUR_OF_DAY, duration)
    return outputFormat.format(calendar.time)
}

internal fun Boolean.formatBooleanToString(trueToString: String, falseToString: String): String {
    return if (this) trueToString else falseToString
}
