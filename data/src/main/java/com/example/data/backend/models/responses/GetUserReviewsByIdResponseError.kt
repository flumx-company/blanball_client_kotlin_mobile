package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetUserReviewsByIdResponseError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: GetUserReviewsByIdResponseErrorData,
    @Json(name = "message") val message: Any? = null,
    @Json(name = "status") val status: String
)

data class GetUserReviewsByIdResponseErrorData(
    @Json(name = "errors") val errors: List<GetUserReviewsByIdResponseDetailData>,
    @Json(name = "type") val type: String
)

data class GetUserReviewsByIdResponseDetailData(
    @Json(name = "detail") val detail: String
)
