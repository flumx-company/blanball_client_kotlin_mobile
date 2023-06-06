package com.example.blanball.utils.ext

internal fun String.isInvalidValidPhoneNumber() =
    (length !in 9..9 && isNotEmpty()) || any { !it.isDigit() }

internal fun String.isValidPhoneNumber() =
    length == 9 && none { !it.isDigit() }

internal fun String.isNotInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length !in min..max && isNotEmpty()

internal fun String.isInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length in min..max

internal fun String.isNotValidEmail(): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return !emailRegex.matches(this) && isNotEmpty()
}

internal fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return emailRegex.matches(this)
}