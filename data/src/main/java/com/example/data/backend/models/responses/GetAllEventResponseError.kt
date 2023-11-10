package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetAllEventResponseError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetAllEventResponseErrorData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class GetAllEventResponseErrorData(
    @Json (name = "errors") val errors: List<GetAllEventResponseErrorDetail>,
    @Json (name = "type") val type: String
)

data class GetAllEventResponseErrorDetail(
    @Json (name = "detail") val detail: String
)