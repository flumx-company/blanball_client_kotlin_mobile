package com.example.domain.entity

data class LoginDataError(
    val errors: List<LoginError>,
    val type: String
)