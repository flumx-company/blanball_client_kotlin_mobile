package com.example.blanball.utils.ext

fun String.isValidPhoneNumber() =
    length == 9 && none { !it.isDigit() }

fun String.isInvalidValidPhoneNumber() =
    (length !in 9..9 && isNotEmpty()) || any { !it.isDigit() }

fun String.isInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length in min..max

fun String.isNotInReqRange(min: Int, max: Int = Int.MAX_VALUE) = this.length !in min..max && isNotEmpty()

fun String.isValidEmail(): Boolean {
    val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
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
