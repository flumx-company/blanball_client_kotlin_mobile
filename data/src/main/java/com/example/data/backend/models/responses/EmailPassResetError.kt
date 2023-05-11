package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class EmailPassResetError(
   @Json(name = "code") val code: Int,
   @Json(name = "`data`") val `data`: EmailPassDataError,
   @Json(name = "message") val message: Any,
   @Json(name = "status") val status: String
)

data class EmailPassDataError(
   @Json (name = "errors") val errors: List<EmailPassResetErrors>,
   @Json (name = "type") val type: String
)

data class EmailPassResetErrors(
    @Json(name = "detail") val detail: String
)
