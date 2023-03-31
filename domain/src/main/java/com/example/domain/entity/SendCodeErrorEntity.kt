package com.example.domain.entity

data class SendCodeErrorEntity(
    val code: Int,
    val `data`: SendCodeDataErrorEntity,
    val message: Any? = null,
    val status: String
)