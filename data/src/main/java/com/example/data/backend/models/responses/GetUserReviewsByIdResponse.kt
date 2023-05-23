package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetUserReviewsByIdResponse(
    @Json (name = " code")  val code: Int,
    @Json (name = "`data`") val `data`: GetUserReviewsByIdData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class GetUserReviewsByIdData (
    @Json (name = "current_page") val current_page: Int,
    @Json (name = "next") val next: String,
    @Json (name = "page_size") val page_size: Int,
    @Json (name = "previous") val previous: Any? = null,
    @Json (name = "results") val results: List<GetUserReviewsByIdResponseResult>? = null,
    @Json (name = "success") val success: Boolean,
    @Json (name = "total_count") val total_count: Int
)

data class GetUserReviewsByIdResponseResult(
    @Json (name = "author") val author: GetUserReviewsByIdResponseAuthor,
    @Json (name = "id") val id: Int,
    @Json (name = "stars") val stars: Int,
    @Json (name = "text") val text: String,
    @Json (name = "time_created") val time_created: String
)

data class GetUserReviewsByIdResponseAuthor(
    @Json (name = "id") val id: Int,
    @Json (name = "profile") val profile: GetUserReviewsByIdResponseProfile
)

data class GetUserReviewsByIdResponseProfile(
    @Json (name = "last_name") val last_name: String,
    @Json (name = "name") val name: String
)