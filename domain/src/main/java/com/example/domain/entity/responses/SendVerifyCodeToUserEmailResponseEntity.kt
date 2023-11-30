package com.example.domain.entity.responses

data class SendVerifyCodeToUserEmailResponseEntity(
    val code: Int,
    val `data`: SendVerifyCodeToUserEmailResponseEntityData,
    val message: String? = null,
    val status: String
)

data class SendVerifyCodeToUserEmailResponseEntityData(
    val success: String
)