package com.example.data.backend.models.responses

data class SendVerifyCodeToUserEmailResponse(
    val code: Int,
    val `data`: SendVerifyCodeToUserEmailResponseData,
    val message: String? = null,
    val status: String
)

data class SendVerifyCodeToUserEmailResponseData(
    val success: String
)