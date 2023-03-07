package com.example.domain.entity

    data class LoginDataEntity(
        val email: String,
        val tokens: TokensEntity,
        val type: String,
        val errors: List<LoginErrorEntity>,
    )