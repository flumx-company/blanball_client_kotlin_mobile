package com.example.data.backend.models

import com.squareup.moshi.Json

data class Data(
    @Json (name = "email") val email: String,
    @Json (name = "tokens") val tokens: Tokens,
    @Json (name = "type") val type: String,
    @Json (name = "errors") val errors: List<Error>,
)