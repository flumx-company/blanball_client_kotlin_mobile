package com.example.domain.entity

data class ResetCompleteResponseEntity(
    val code: Int,
    val `data`: DataCompleteResponseEntity,
    val message: Any? = null,
    val status: String,
)
