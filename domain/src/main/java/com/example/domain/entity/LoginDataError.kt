package com.example.domain.entity

data class LoginDataError(
    val errors: List<LoginErrorDomain>,
    val type: String
)