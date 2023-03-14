package com.example.data.backend.models

data class LoginDto(
    val code: Int,
    val `data`: Data,
    val message: Any? = null,
    val status: String
    )

