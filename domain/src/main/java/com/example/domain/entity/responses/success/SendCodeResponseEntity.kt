package com.example.domain.entity.responses.success

data class SendCodeResponseEntity(
    val code: Int,
    val `data`: DataSendCodeDomain,
    val message: Any? = null,
    val status: String
)

data class SendCodeErrorsEntity(
    val detail: String
)

data class SendCodeErrorEntity(
    val code: Int,
    val `data`: SendCodeDataErrorEntity,
    val message: Any? = null,
    val status: String
)

data class SendCodeDataErrorEntity(
    val errors: List<SendCodeErrorsEntity>,
    val type: String
)

data class DataSendCodeDomain(
    val success: String
)