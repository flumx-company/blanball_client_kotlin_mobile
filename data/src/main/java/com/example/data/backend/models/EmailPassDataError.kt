package com.example.data.backend.models

data class EmailPassDataError(
    val errors: List<EmailPassResetErrors>,
    val type: String
)