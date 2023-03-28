package com.example.data.backend.models

import com.squareup.moshi.Json

data class ResetPassRequestSuccess(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: DataResetPassRequest,
    @Json (name = "message") val message: Any,
    @Json (name = "status") val status: String
)
