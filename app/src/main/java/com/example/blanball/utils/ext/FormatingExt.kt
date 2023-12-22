package com.example.blanball.utils.ext

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.MutableState
import com.example.blanball.R
import com.example.blanball.presentation.data.EditEventScreenMainContract
import com.example.blanball.presentation.data.EventEditAndCreationScreensMainContract
import com.example.domain.utils.Formats
import com.example.domain.utils.Integers
import com.google.android.gms.maps.model.LatLng
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
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

internal fun Any?.formatRatingToString(): String {
    if (this == null) {
        return Formats.DECIMAL_FORMAT
    }
    val decimalFormat = DecimalFormat(Formats.DECIMAL_FORMAT)
    decimalFormat.decimalFormatSymbols = DecimalFormatSymbols.getInstance(Locale.US)
    return decimalFormat.format(this)
}

internal fun String.formatDatePlannedEventsToTime(duration: Int = Integers.ZERO): String {
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

internal fun String.formatWorkingLegToEnglishWord(context: Context): String {
    return if (this == context.resources.getString(R.string.right_leg)) (context.resources.getString(
        R.string.right
    ))
    else (context.resources.getString(R.string.left))
}

internal fun String.formatPositionToEnglish(context: Context): String {
    return when (this) {
        context.resources.getString(R.string.goalkeeper) -> context.resources.getString(R.string.gk)
        context.resources.getString(R.string.left_defender) -> context.resources.getString(R.string.lb)
        context.resources.getString(R.string.right_defender) -> context.resources.getString(R.string.rb)
        context.resources.getString(R.string.central_defender) -> context.resources.getString(R.string.cb)
        context.resources.getString(R.string.left_flank_defender) -> context.resources.getString(R.string.lwb)
        context.resources.getString(R.string.right_flank_defender) -> context.resources.getString(R.string.rwb)
        context.resources.getString(R.string.supporting_mid_defender) -> context.resources.getString(
            R.string.cdm
        )

        context.resources.getString(R.string.left_mid_defender) -> context.resources.getString(R.string.lm)
        context.resources.getString(R.string.attacking_mid_defender) -> context.resources.getString(
            R.string.cam
        )

        context.resources.getString(R.string.right_winger) -> context.resources.getString(R.string.rm)
        context.resources.getString(R.string.left_winger) -> context.resources.getString(R.string.lw)
        context.resources.getString(R.string.right_flank_attacker) -> context.resources.getString(R.string.rw)
        context.resources.getString(R.string.left_flank_attacker) -> context.resources.getString(R.string.lw)
        context.resources.getString(R.string.right_forward) -> context.resources.getString(R.string.rf)
        context.resources.getString(R.string.central_forward) -> context.resources.getString(R.string.cf)
        context.resources.getString(R.string.left_forward) -> context.resources.getString(R.string.lf)
        context.resources.getString(R.string.forward_striker) -> context.resources.getString(R.string.st)
        else -> ""
    }
}

internal fun String.formatSportTypeToEnglish(context: Context): String {
    return when (this) {
        context.resources.getString(R.string.football) -> context.resources.getString(R.string.football_us)
        context.resources.getString(R.string.futsal) -> context.resources.getString(R.string.futsal)
        else -> ""
    }
}

internal fun <T : Enum<T>> T.EventPrivacyStatesToBoolean(): Boolean {
    return when (this) {
        is EventEditAndCreationScreensMainContract.EventPrivacyStates -> {
            when (this) {
                EventEditAndCreationScreensMainContract.EventPrivacyStates.YES -> true
                EventEditAndCreationScreensMainContract.EventPrivacyStates.NO -> false
                else -> false
            }
        }

        is EditEventScreenMainContract.EventPrivacyStates -> {
            when (this) {
                EditEventScreenMainContract.EventPrivacyStates.YES -> true
                EditEventScreenMainContract.EventPrivacyStates.NO -> false
                else -> false
            }
        }

        else -> false
    }
}

internal fun EventEditAndCreationScreensMainContract.EventPrivacyStates.EventPrivacyStatesToBoolean(): Boolean {
    return when (this) {
        EventEditAndCreationScreensMainContract.EventPrivacyStates.YES -> true
        EventEditAndCreationScreensMainContract.EventPrivacyStates.NO -> false
        else -> false
    }
}

internal fun <T : Enum<T>> T.NeedFormStatesToBoolean(): Boolean {
    return when (this) {
        is EventEditAndCreationScreensMainContract.NeedFormStates -> {
            when (this) {
                EventEditAndCreationScreensMainContract.NeedFormStates.YES -> true
                EventEditAndCreationScreensMainContract.NeedFormStates.NO -> false
                else -> false
            }
        }

        is EditEventScreenMainContract.NeedFormStates -> {
            when (this) {
                EditEventScreenMainContract.NeedFormStates.YES -> true
                EditEventScreenMainContract.NeedFormStates.NO -> false
                else -> false
            }
        }

        else -> false
    }
}

internal fun <T : Enum<T>> T.PlayersGenderStatesToString(context: Context): String {
    return when (this) {
        is EventEditAndCreationScreensMainContract.PlayersGenderStates -> {
            when (this) {
                EventEditAndCreationScreensMainContract.PlayersGenderStates.MANS -> context.resources.getString(
                    R.string.man
                )

                EventEditAndCreationScreensMainContract.PlayersGenderStates.WOMANS -> context.resources.getString(
                    R.string.woman
                )

                EventEditAndCreationScreensMainContract.PlayersGenderStates.NO_SELECT -> ""
            }
        }

        is EditEventScreenMainContract.PlayersGenderStates -> {
            when (this) {
                EditEventScreenMainContract.PlayersGenderStates.MANS -> context.resources.getString(
                    R.string.man
                )

                EditEventScreenMainContract.PlayersGenderStates.WOMANS -> context.resources.getString(
                    R.string.woman
                )

                EditEventScreenMainContract.PlayersGenderStates.NO_SELECT -> ""
            }
        }

        else -> ""
    }
}

internal fun String.SportTypesStringsToEnglish(context: Context): String {
    return when (this) {
        context.resources.getString(R.string.football) -> context.resources.getString(R.string.football_us)
        context.resources.getString(R.string.futsal) -> context.resources.getString(R.string.futsal_us)
        else -> ""
    }
}

internal fun EventEditAndCreationScreensMainContract.PlayersGenderStates.PlayersGenderStatesToUkrainianString(
    context: Context
): String {
    return when (this) {
        EventEditAndCreationScreensMainContract.PlayersGenderStates.MANS -> context.resources.getString(R.string.man_ukr)
        EventEditAndCreationScreensMainContract.PlayersGenderStates.WOMANS -> context.resources.getString(R.string.woman_ukr)
        EventEditAndCreationScreensMainContract.PlayersGenderStates.NO_SELECT -> ""
    }
}

internal fun String.formatToUkrainianDate(): String {
    if (this.isEmpty()) {
        return ""
    }
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale("uk", "UA"))
    val outputFormat = SimpleDateFormat("d MMMM", Locale("uk", "UA"))

    val date = inputFormat.parse(this)
    return date?.let { outputFormat.format(it) } ?: ""
}

fun formatToIso8601DateTime(date: String, time: String): String {
    if (date.isEmpty() || time.isEmpty()) {
        return ""
    }
    val inputDateTime = "$date $time"
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    val dateTime = inputFormat.parse(inputDateTime)
    return dateTime?.let { outputFormat.format(it) } ?: ""
}

fun String.calculateTimeDifferenceInMinutes(endTime: String): Int {

    val dateFormat = SimpleDateFormat("HH:mm")

    if (endTime.isEmpty()) {
        return 0
    }
    val startTime = dateFormat.parse(this)
    val endTimeDate = dateFormat.parse(endTime)

    val timeDifferenceMillis = endTimeDate.time - startTime.time

    return (timeDifferenceMillis / (60 * 1000)).toInt()
}

internal fun MutableState<String>.calculateValueWithEventDuration(eventDuration: MutableState<Int>): String {
    if (this.value.isEmpty()) {
        return ""
    }
    return if (eventDuration.value != 0) {
        val startTime = SimpleDateFormat("HH:mm").parse(this.value)
        val endTime = Date(startTime.time + (eventDuration.value * 60 * 1000))
        SimpleDateFormat("HH:mm").format(endTime)
    } else {
        this.value ?: ""
    }
}

internal fun String.formatTimeRange(durationInMinutes: Int): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    try {
        val startTime = dateFormat.parse(this)
        val endTime = Date(startTime.time + durationInMinutes * 60 * 1000)

        val startTimeFormatted = SimpleDateFormat("HH:mm").format(startTime)
        val endTimeFormatted = SimpleDateFormat("HH:mm").format(endTime)

        return "$startTimeFormatted - $endTimeFormatted"
    } catch (e: Exception) {
        return ""
    }
}

fun String.toFormattedDate(): String {
    if (this.isEmpty()) {
        return ""
    }
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("uk", "UA"))

    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date)
    } catch (e: Exception) {
        this
    }
}

internal fun String.convertToPositionCode(context: Context): String? {
    return when (this) {
        context.getString(R.string.any_position) -> null
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
        else -> null
    }
}

internal fun String?.toFormattedBirthdayDate(): String? {
    if (this?.isEmpty() == true) {
        return ""
    }
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy 'р.'", Locale("uk", "UA"))

    return try {
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) }
    } catch (e: Exception) {
        return this
    }
}

internal fun String.calculateAge(): String {
    if (this.isEmpty()) {
        return ""
    }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val currentDate = Calendar.getInstance().time
    val birthDate = dateFormat.parse(this)

    val diff = currentDate.time - birthDate.time
    val age = diff / (1000L * 60 * 60 * 24 * 365)

    return age.toString()
}

internal fun String.addMinutes(minutes: Int): String {
    if (minutes == 0){
        return ""
    }
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val calendar =  Calendar.getInstance()
    calendar.time  = sdf.parse(this) ?: Date()
    calendar.add(Calendar.MINUTE, minutes)
    return sdf.format(calendar.time)
}

internal fun LatLng.getAddressFromLocation(
    context: Context,
) : String? {
    val geocoder = Geocoder(context, Locale("uk", "UA"))
    val addresses = geocoder.getFromLocation(this.latitude, this.longitude, 1)
    if (addresses?.isNotEmpty() == true) {
        val address = addresses[0]
        return address.getAddressLine(0)
    }
    return null
}