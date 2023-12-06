package com.example.domain.entity.responses.errors

data class GetMyProfileErrorEntity(
    val code: Int,
    val `data`: GetMyProfileErrorDataEntity,
    val message: String? = null,
    val status: String
)

data class GetMyProfileErrorDataEntity(
    val errors: List<GetMyProfileErrorDetailEntity>,
    val type: String
)

data class GetMyProfileErrorDetailEntity(
    val detail: String
)