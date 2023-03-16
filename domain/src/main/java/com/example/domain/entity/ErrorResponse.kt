package com.example.domain.entity

data class ErrorResponse(
    val code: Int,
    val `data`: LoginDataError,
    val message: Any? = null,
    val status: String
)
