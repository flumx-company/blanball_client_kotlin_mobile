package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class SendCodeError(
    @Json (name = "code")val code: Int,
    @Json (name = "`data")val `data`: SendCodeDataError,
    @Json (name = "message")val message: Any? = null,
    @Json (name = "status")val status: String
)

data class SendCodeDataError(
    @Json(name = "errors") val errors: List<SendCodeErrors>,
    @Json(name = "type") val type: String
)

data class SendCodeErrors(
   @Json(name = "detail") val detail: String
)