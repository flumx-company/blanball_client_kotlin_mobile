package com.example.blanball.utils.ext

fun String.isInvalidValidPhoneNumber() =
    (length !in 9..9 && isNotEmpty()) || any { !it.isDigit() }

fun String.isValidPhoneNumber() =
    length == 9 && none { !it.isDigit() }

fun String.isNotInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length !in min..max && isNotEmpty()

fun String.isInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length in min..max

fun String.isNotValidEmail(): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return !emailRegex.matches(this) && isNotEmpty()
}

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
    return emailRegex.matches(this)
}