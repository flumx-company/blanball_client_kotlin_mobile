package com.example.blanball.utils.ext

import android.content.Context
import com.example.blanball.R
import com.example.blanball.presentation.data.EventCreationScreenMainContract
import com.example.domain.utils.Formats
import com.example.domain.utils.Integers
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
    return if (this == context.resources.getString(R.string.right_leg)) (context.resources.getString(R.string.right))
    else (context.resources.getString(R.string.left))
}

internal fun String.formatPositionToEnglish(context: Context): String {
  return   when (this) {
      context.resources.getString(R.string.goalkeeper) -> context.resources.getString(R.string.gk)
      context.resources.getString(R.string.left_defender) -> context.resources.getString(R.string.lb)
      context.resources.getString(R.string.right_defender) -> context.resources.getString(R.string.rb)
      context.resources.getString(R.string.central_defender) -> context.resources.getString(R.string.cb)
      context.resources.getString(R.string.left_flank_defender) -> context.resources.getString(R.string.lwb)
      context.resources.getString(R.string.right_flank_defender) -> context.resources.getString(R.string.rwb)
      context.resources.getString(R.string.supporting_mid_defender) -> context.resources.getString(R.string.cdm)
      context.resources.getString(R.string.left_mid_defender) -> context.resources.getString(R.string.lm)
      context.resources.getString(R.string.attacking_mid_defender) -> context.resources.getString(R.string.cam)
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

internal fun EventCreationScreenMainContract.EventPrivacyStates.EventPrivacyStatesToBoolean(): Boolean {
    return when (this) {
        EventCreationScreenMainContract.EventPrivacyStates.YES -> true
        EventCreationScreenMainContract.EventPrivacyStates.NO -> false
        else -> false
    }
}

internal fun EventCreationScreenMainContract.NeedFormStates.NeedFormStatesToBoolean(): Boolean {
    return when (this) {
        EventCreationScreenMainContract.NeedFormStates.YES -> true
        EventCreationScreenMainContract.NeedFormStates.NO -> false
        else -> false
    }
}

internal fun EventCreationScreenMainContract.PlayersGenderStates.PlayersGenderStatesToString(context: Context): String {
    return when (this) {
        EventCreationScreenMainContract.PlayersGenderStates.MANS -> context.resources.getString(R.string.man)
        EventCreationScreenMainContract.PlayersGenderStates.WOMANS -> context.resources.getString(R.string.woman)
        EventCreationScreenMainContract.PlayersGenderStates.NO_SELECT -> ""
    }
}

internal fun EventCreationScreenMainContract.PlayersGenderStates.PlayersGenderStatesToUkrainianString(context: Context): String {
    return when (this) {
        EventCreationScreenMainContract.PlayersGenderStates.MANS -> context.resources.getString(R.string.man_ukr)
        EventCreationScreenMainContract.PlayersGenderStates.WOMANS -> context.resources.getString(R.string.woman_ukr)
        EventCreationScreenMainContract.PlayersGenderStates.NO_SELECT -> ""
    }
}