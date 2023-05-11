package com.example.domain.entity.responses

data class EmailResetResponseEntity(
    val code: Int,
    val `data`: DataEmailResetEntity,
    val message: Any? = null,
    val status: String
)

data class DataEmailResetEntity(
    val success: String
)

data class EmailPassDataErrorEntity(
    val errors: List<EmailPassResetErrorsEntity>,
    val type: String
)

data class EmailPassResetErrorsEntity(
    val detail: String
)

data class EmailPassResetErrorEntity(
    val code: Int,
    val `data`: EmailPassDataErrorEntity,
    val message: Any? = null,
    val status: String
)