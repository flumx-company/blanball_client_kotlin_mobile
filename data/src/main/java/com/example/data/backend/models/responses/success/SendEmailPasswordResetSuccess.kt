package com.example.data.backend.models.responses.success

import com.squareup.moshi.Json

data class SendEmailPasswordResetSuccess (
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: DataEmailReset,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class DataEmailReset(
@Json(name = "success") val success: String
)