package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class ResetCompleteError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data'") val `data`: DataResetCompleteError,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class DataResetCompleteError(
    @Json(name = "errors") val errors: List<ResetCompleteErrors>,
    @Json(name = "type") val type: String,
)

data class ResetCompleteErrors(
   @Json(name = "detail") val detail: String
)