package com.example.data.backend.models

data class EmailPassResetError(
    val code: Int,
    val `data`: EmailPassDataError,
    val message: Any,
    val status: String
)