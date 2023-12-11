package com.example.domain.entity.responses.errors

data class GetUserReviewsByIdResponseErrorEntity(
    val code: Int,
    val `data`: GetUserReviewsByIdResponseErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class GetUserReviewsByIdResponseErrorDataEntity(
    val errors: List<GetUserReviewsByIdResponseDetailDataEntity>,
    val type: String
)

data class GetUserReviewsByIdResponseDetailDataEntity(
    val detail: String
)
