package com.example.domain.entity.responses.errors

data class GetUserProfileByIdErrorEntity(
    val code: Int,
    val `data`: GetUserProfileByIdErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class GetUserProfileByIdErrorDataEntity(
    val errors: List<GetUserProfileByIdDetailDataEntity>,
    val type: String
)

data class GetUserProfileByIdDetailDataEntity(
    val detail: String
)
