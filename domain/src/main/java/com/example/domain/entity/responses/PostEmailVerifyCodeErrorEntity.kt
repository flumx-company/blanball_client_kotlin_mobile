package com.example.domain.entity.responses

data class PostEmailVerifyCodeErrorEntity(
    val code: Int,
    val `data`: PostEmailVerifyCodeDataErrorEntity,
    val message: Any? = null,
    val status: String
)

data class PostEmailVerifyCodeDataErrorEntity(
    val errors: List<PostEmailVerifyCodeErrorEntityDetail>,
    val type: String
)

data class PostEmailVerifyCodeErrorEntityDetail(
    val detail: String
)