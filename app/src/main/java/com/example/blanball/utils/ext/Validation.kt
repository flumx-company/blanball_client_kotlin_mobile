package com.example.blanball.utils.ext

import com.example.domain.utils.Formats

fun String.isValidPhoneNumber() =
    length == 9 && none { !it.isDigit() }

fun String.isInvalidValidPhoneNumber() =
    (length !in 9..9 && isNotEmpty()) || any { !it.isDigit() }

fun String.isInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length in min..max

fun String.isNotInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length !in min..max && isNotEmpty()

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex(Formats.EMAIl_FORMAT)
    return emailRegex.matches(this)
}

fun String.isNotValidEmail(): Boolean {
    return !isValidEmail() && isNotEmpty()
}

fun String.isValidCode(): Boolean {
    return all { it.isLetter() } && length == 5
}

fun String.isNotValidCode(): Boolean {
    return !isValidCode() && isNotEmpty()
}

fun String.isValidUserName(): Boolean {
    return all{it.isLetter()}
}

fun String.isNotValidUserName(): Boolean {
    return !isValidUserName()
}
