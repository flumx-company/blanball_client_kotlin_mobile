package com.example.domain.entity

data class RegistrationResponseEntity(
    val code: Int,
    val `data`: RegistrationDataEntity,
    val message: Any,
    val status: String
)

data class RegistrationDataEntity(
    val access: String,
    val refresh: String
)
