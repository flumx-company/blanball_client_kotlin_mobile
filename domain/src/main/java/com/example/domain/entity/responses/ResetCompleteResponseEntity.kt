package com.example.domain.entity.responses

data class ResetCompleteResponseEntity(
    val code: Int,
    val `data`: DataCompleteResponseEntity,
    val message: Any? = null,
    val status: String,
)

data class ResetCompleteDataEntity(
    val errors: List<ResetCompleteErrorsEntity>,
    val type: String
)

data class ResetCompleteErrorsEntity(
    val detail: String
)

data class ResetCompleteErrorEntity(
    val code: Int,
    val `data`: ResetCompleteDataEntity,
    val message: Any? = null,
    val status: String
)

data class DataCompleteResponseEntity(
    val success: String,
)