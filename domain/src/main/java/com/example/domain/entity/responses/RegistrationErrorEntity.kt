package com.example.domain.entity.responses

data  class RegistrationErrorEntity (
    val code: Int,
    val `data`: RegistrationErrorsDataEntity,
    val message: Any? = null,
    val status: String   )

    data class RegistrationErrorsDataEntity(
        val errors: List<RegistrationErrorDetailEntity>,
        val type: String
    )

    data class RegistrationErrorDetailEntity(
        val detail: String
    )
