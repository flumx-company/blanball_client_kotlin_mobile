package com.example.domain.entity

data class SendCodeDataErrorEntity(
    val errors: List<SendCodeErrorsEntity>,
    val type: String
)