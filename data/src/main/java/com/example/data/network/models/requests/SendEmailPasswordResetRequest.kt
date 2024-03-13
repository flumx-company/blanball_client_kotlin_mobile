package com.example.data.network.models.requests

import com.squareup.moshi.Json

data class SendEmailPasswordResetRequest(
    @Json (name = "email") val email: String,
)
