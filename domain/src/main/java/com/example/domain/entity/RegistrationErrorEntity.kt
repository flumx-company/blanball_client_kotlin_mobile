package com.example.domain.entity

data  class RegistrationErrorEntity (
        val code: Int,
        val `data`: RegistrationErrorDataEntity,
        val message: Any,
        val status: String,
    )

    data class RegistrationErrorDataEntity(
        val errors: List<Error>,
        val type: String
    )

    data class Error(
        val detail: String
    )
