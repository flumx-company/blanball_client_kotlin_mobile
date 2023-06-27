package com.example.blanball.utils.ext

import com.example.domain.utils.Formats
import com.example.domain.utils.Integers
import java.util.Calendar

internal fun String.isValidPhoneNumber() =
    length == 9 && none { !it.isDigit() }

internal fun String.isInvalidValidPhoneNumber() =
    (length !in 9..9 && isNotEmpty()) || any { !it.isDigit() }

internal fun String.isInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length in min..max

internal fun String.isNotInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length !in min..max && isNotEmpty()

internal fun String.isValidEmail(): Boolean {
    val emailRegex = Regex(Formats.EMAIl_FORMAT)
    return emailRegex.matches(this)
}

internal fun String.isNotValidEmail(): Boolean {
    return !isValidEmail() && isNotEmpty()
}

internal fun String.isValidCode(): Boolean {
    return all { it.isLetter() } && length == Integers.FIVE
}

internal fun String.isNotValidCode(): Boolean {
    return !isValidCode() && isNotEmpty()
}

internal fun String.isValidUserName(): Boolean {
    return all{it.isLetter()}
}

internal fun String.isNotValidUserName(): Boolean {
    return !isValidUserName()
}

internal fun String.isValidBirthDay(): Boolean {
    return this.matches(Regex("0[1-9]|[12][0-9]|3[01]"))
}

internal fun String.isNotValidBirthDay(): Boolean {
    return !isValidBirthDay() && isNotEmpty()
}

internal fun String.isValidBirthMonth(): Boolean {
    return this.matches(Regex("0[1-9]|1[0-2]"))
}

internal fun String.isNotValidBirthMonth(): Boolean {
    return !isValidBirthMonth() && isNotEmpty()
}

internal fun String.isValidBirthYear(): Boolean {
    val year = this.toIntOrNull()
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    return year != null && year in currentYear - Integers.EIGHTY until currentYear - Integers.SIX
}

internal fun String.isNotValidBirthYear(): Boolean {
    return !isValidBirthYear() && isNotEmpty()
}

internal fun String.isValidHeight(): Boolean {
    val height = this.toIntOrNull()
    return height != null && height in Integers.THIRTY..Integers.TWO_HUNDRED_AND_TEN
}

internal fun String.isNotValidHeight(): Boolean {
    return !isValidHeight() && isNotEmpty()
}

internal fun String.isValidWeight(): Boolean {
    val weight = this.toIntOrNull()
    return weight != null && weight in Integers.THIRTY..Integers.TWO_HUNDRED_AND_TEN
}

internal fun String.isNotValidWeight(): Boolean {
    return !isValidWeight() && isNotEmpty()
}