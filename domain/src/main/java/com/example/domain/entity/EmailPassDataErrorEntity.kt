package com.example.domain.entity

data class EmailPassDataErrorEntity(
    val errors: List<EmailPassResetErrorsEntity>,
    val type: String
)