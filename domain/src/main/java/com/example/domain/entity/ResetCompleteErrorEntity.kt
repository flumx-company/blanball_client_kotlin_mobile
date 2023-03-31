package com.example.domain.entity

data class ResetCompleteErrorEntity(
    val code: Int,
    val `data`: ResetCompleteDataEntity,
    val message: Any? = null,
    val status: String
)