package com.example.data.backend.models

import com.squareup.moshi.Json

data class DataResetCompleteError(
    @Json (name = "errors") val errors: List<ResetCompleteErrors>,
    @Json (name = "type") val type: String,
)