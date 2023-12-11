package com.example.domain.entity.responses.errors

data class SendVerifyCodeToUserEmailErrorEntity(
    val code: Int,
    val data: SendVerifyCodeToUserEmailErrorEntityData,
    val message: String? = null,
    val status: String?
)

data class SendVerifyCodeToUserEmailErrorEntityData(
    val errors: List<SendVerifyCodeToUserEmailErrorEntityDataDetail>,
    val type: String
)

data class SendVerifyCodeToUserEmailErrorEntityDataDetail(
    val detail: String
)