package com.example.data.backend.models

data class RegistrationError(
    val code: Int,
    val `data`: RegistrationErrorsData,
    val message: Any,
    val status: String
)

data class RegistrationErrorsData(
    val errors: List<RegistrationErrorDetail>,
    val type: String
)

data class RegistrationErrorDetail(
    val detail: String
)