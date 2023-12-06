package com.example.data.backend.models.responses.errors

import com.squareup.moshi.Json

data class LoginError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: DataError,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class DataError(
    @Json(name = "errors") val errors: List<LoginErrors>,
    @Json(name = "type") val type: String
)

data class LoginErrors(
    @Json(name = "detail") val detail: String
)