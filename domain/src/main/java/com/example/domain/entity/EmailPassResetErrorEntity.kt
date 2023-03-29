package com.example.domain.entity

data class EmailPassResetErrorEntity(
    val code: Int,
    val `data`: EmailPassDataErrorEntity,
    val message: Any? = null,
    val status: String
)