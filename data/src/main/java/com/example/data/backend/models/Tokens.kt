package com.example.data.backend.models

import com.squareup.moshi.Json

data class Tokens(
    @Json (name = "access") val access: String,
    @Json (name = "refresh") val refresh: String
)