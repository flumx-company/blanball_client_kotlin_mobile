package com.example.data.backend.models

data class RegistrationError(
    val code: Int,
    val `data`: RegistrationErrorData,
    val message: Any,
    val status: String
)

data class RegistrationErrorData(
    val errors: List<Error>,
    val type: String
)

data class Error(
    val detail: String
)