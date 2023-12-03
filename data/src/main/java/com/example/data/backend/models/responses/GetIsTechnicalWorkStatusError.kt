package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetIsTechnicalWorkStatusError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: GetIsTechnicalWorkStatusErrorData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class GetIsTechnicalWorkStatusErrorData(
    @Json(name = "errors") val errors: List<GetIsTechnicalWorkStatusErrorDetail>,
    @Json(name = "type") val type: String
)

data class GetIsTechnicalWorkStatusErrorDetail(
    @Json(name = "detail") val detail: String
)