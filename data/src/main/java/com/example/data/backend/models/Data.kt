package com.example.data.backend.models

data class Data(
    val email: String,
    val tokens: Tokens,
    val type: String,
    val errors: List<Error>,
)