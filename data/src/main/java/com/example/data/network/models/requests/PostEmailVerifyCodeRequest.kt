package com.example.data.network.models.requests

import com.squareup.moshi.Json

data class PostEmailVerifyCodeRequest(
    @Json(name = "verify_code") val verify_code: String
)
