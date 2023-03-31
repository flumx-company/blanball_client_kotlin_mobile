package com.example.domain.entity

data class LoginDataError(
    val errors: List<LoginErrorsDomain>,
    val type: String
)