package com.example.domain.entity

data class LoginResponse(
    val code: Int,
    val `data`: LoginData,
    val message: Any? = null,
    val status: String
)
