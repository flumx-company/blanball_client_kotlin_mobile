package com.example.domain.entity.responses

data class PostEmailVerifyCodeResponseEntity(
    val code: Int,
    val `data`: PostEmailVerifyCodeResponseEntityData,
    val message: Any? = null,
    val status: String
)

data class PostEmailVerifyCodeResponseEntityData(
    val success: String
)