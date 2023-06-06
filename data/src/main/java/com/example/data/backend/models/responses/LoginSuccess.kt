package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class LoginSuccess(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: Data,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
    )

data class Data(
    @Json(name = "email") val email: String,
    @Json(name = "tokens") val tokens: Tokens
)

data class Tokens(
    @Json(name = "access") val access: String,
    @Json(name = "refresh") val refresh: String
)