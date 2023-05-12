package com.example.data.backend.models.responses

data class GetUserReviewsByIdResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Result>
)

data class Result(
    val author: Author,
    val id: Int,
    val stars: Int,
    val text: String,
    val time_created: String
)

data class Author(
    val id: Int,
    val profile: ProfileGetUserReviewsByIdResponse
)

data class ProfileGetUserReviewsByIdResponse (
    val last_name: String,
    val name: String
)