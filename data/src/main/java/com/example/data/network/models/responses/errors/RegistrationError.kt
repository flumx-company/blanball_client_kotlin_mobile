package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class RegistrationError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: RegistrationErrorsData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class RegistrationErrorsData(
    @Json (name = "errors") val errors: List<RegistrationErrorDetail>,
    @Json (name = "type") val type: String
)

data class RegistrationErrorDetail(
   @Json (name = "detail") val detail: String
)