package com.example.domain.entity.responses.success

data class GetUserReviewsByIdResponseEntity(
    val code: Int,
    val `data`: GetUserReviewsByIdDataEntity,
    val message: String? = null,
    val status: String
)

data class GetUserReviewsByIdDataEntity(
    val current_page: Int,
    val next: String? = null,
    val page_size: Int,
    val previous: String? = null,
    val results: List<GetUserReviewsByIdResponseResultEntity>? = null,
    val success: Boolean,
    val total_count: Int
)

data class GetUserReviewsByIdResponseResultEntity(
    val author: GetUserReviewsByIdResponseAuthorEntity,
    val id: Int,
    val stars: Int,
    val text: String,
    val time_created: String
)

data class GetUserReviewsByIdResponseAuthorEntity(
    val id: Int,
    val profile: GetUserReviewsByIdResponseProfileEntity
)

data class GetUserReviewsByIdResponseProfileEntity(
    val last_name: String,
    val name: String
)