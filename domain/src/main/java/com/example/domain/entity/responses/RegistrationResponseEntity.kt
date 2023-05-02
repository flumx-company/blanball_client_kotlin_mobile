package com.example.domain.entity.responses

data class RegistrationResponseEntity(
    val code: Int,
    val `data`: RegistrationDataEntity,
    val message: Any? = null,
    val status: String
)

data class RegistrationDataEntity(
    val access: String,
    val refresh: String
)
