package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class CreationAnEventError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: CreationAnEventErrorData,
    @Json(name = "message") val message: Any? = null,
    @Json(name = "status") val status: String
)

data class CreationAnEventErrorData(
    @Json(name = "errors") val errors: List<CreationAnEventErrorDetail>,
    @Json(name = "type") val type: String
)

data class CreationAnEventErrorDetail(
    @Json(name = "detail") val detail: String
)