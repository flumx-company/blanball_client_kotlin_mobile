package com.example.domain.entity.responses.errors

data class GetIsTechnicalWorkStatusErrorEntity(
    val code: Int,
    val `data`: GetIsTechnicalWorkStatusErrorDataEntity,
    val message: String? = null,
    val status: String
)

data class GetIsTechnicalWorkStatusErrorDataEntity(
    val errors: List<GetIsTechnicalWorkStatusErrorDetailEntity>,
    val type: String
)

data class GetIsTechnicalWorkStatusErrorDetailEntity(
    val detail: String
)