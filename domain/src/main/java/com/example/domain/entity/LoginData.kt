package com.example.domain.entity

    data class LoginData(
        val email: String,
        val tokens: LoginTokens,
        val type: String,
        val errors: LoginError,
    )