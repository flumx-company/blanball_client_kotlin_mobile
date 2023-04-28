package com.example.data.backend.models

import com.squareup.moshi.Json

data class RegistrationResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: RegistrationData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class RegistrationData(
    @Json (name = "access") val access: String,
    @Json (name = "refresh") val refresh: String
)