package com.example.blanball.utils.ext

import android.content.Context
import com.example.blanball.R
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

internal fun Any.formatRatingToFloat(): Float {
    return when (this) {
        is Double -> this.toFloat()
        else -> 0f
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

internal fun ClosedFloatingPointRange<Float>.toIntRange(): IntRange {
    return this.start.toInt()..this.endInclusive.toInt()}

internal fun String.convertToPositionCode(context: Context): String {
    return when (this) {
        context.getString(R.string.any_position) -> context.getString(R.string.empty)
        context.getString(R.string.goalkeeper) -> context.getString(R.string.gk)
        context.getString(R.string.left_defender) -> context.getString(R.string.lb)
        context.getString(R.string.right_defender) -> context.getString(R.string.rb)
        context.getString(R.string.central_defender) -> context.getString(R.string.cb)
        context.getString(R.string.left_flank_defender) -> context.getString(R.string.lwb)
        context.getString(R.string.right_flank_defender) -> context.getString(R.string.rwb)
        context.getString(R.string.supporting_mid_defender) -> context.getString(R.string.cdm)
        context.getString(R.string.left_mid_defender) -> context.getString(R.string.cm)
        context.getString(R.string.attacking_mid_defender) -> context.getString(R.string.cam)
        context.getString(R.string.right_winger) -> context.getString(R.string.rm)
        context.getString(R.string.left_winger) -> context.getString(R.string.lm)
        context.getString(R.string.right_flank_attacker) -> context.getString(R.string.rw)
        context.getString(R.string.left_flank_attacker) -> context.getString(R.string.lw)
        context.getString(R.string.right_forward) -> context.getString(R.string.rf)
        context.getString(R.string.central_forward) -> context.getString(R.string.cf)
        context.getString(R.string.left_forward) -> context.getString(R.string.lf)
        context.getString(R.string.forward_striker) -> context.getString(R.string.st)
        else -> "--"
    }
}
