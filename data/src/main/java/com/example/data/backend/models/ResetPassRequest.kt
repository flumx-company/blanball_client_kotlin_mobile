package com.example.data.backend.models

import com.squareup.moshi.Json

data class ResetPassRequest(
    @Json (name = "email") val email: String,
)
