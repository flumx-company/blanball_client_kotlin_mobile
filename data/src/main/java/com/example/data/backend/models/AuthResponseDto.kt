package com.example.data.backend.models

data class AuthResponseDto(
    val code: Int,
    val `data`: Data,
    val message: Any,
    val status: String
)