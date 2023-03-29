package com.example.domain.entity

data class EmailResetResponseEntity(
    val code: Int,
    val `data`: DataEmailResetEntity,
    val message: Any? = null,
    val status: String
)
