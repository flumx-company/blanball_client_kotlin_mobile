package com.example.data.backend.models.requests

import com.squareup.moshi.Json

data class ResetCompleteRequest(
    @Json (name = "new_password") val new_password: String,
    @Json (name = "verify_code") val verify_code: String
)
