package com.example.data.backend.models

import com.squareup.moshi.Json

data class DataError(
    @Json (name = "errors") val errors: List<Error>,
    @Json (name = "type") val type: String
)