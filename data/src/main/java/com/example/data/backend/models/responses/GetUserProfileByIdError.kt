package com.example.data.backend.models.responses

data class GetUserProfileByIdError(
    val code: Int,
    val `data`: GetUserProfileByIdErrorData,
    val message: Any,
    val status: String
)

data class GetUserProfileByIdErrorData(
    val errors: List<GetUserProfileByIdDetailData>,
    val type: String
)

data class GetUserProfileByIdDetailData(
    val detail: String
)
