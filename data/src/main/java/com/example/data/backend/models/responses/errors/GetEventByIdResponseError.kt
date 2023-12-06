package com.example.data.backend.models.responses.errors

import com.squareup.moshi.Json

data class GetEventByIdResponseError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: GetEventByIdResponseErrorData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class GetEventByIdResponseErrorData(
    @Json(name = "errors") val errors: List<GetEventByIdResponseErrorDetail>,
    @Json(name = "type") val type: String
)

data class GetEventByIdResponseErrorDetail(
    @Json(name = "detail") val detail: String
)