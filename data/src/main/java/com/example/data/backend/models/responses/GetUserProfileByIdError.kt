package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetUserProfileByIdError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetUserProfileByIdErrorData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class GetUserProfileByIdErrorData(
    @Json (name = "errors") val errors: List<GetUserProfileByIdDetailData>,
    @Json (name = "type") val type: String
)

data class GetUserProfileByIdDetailData(
    @Json (name = "detail") val detail: String
)
