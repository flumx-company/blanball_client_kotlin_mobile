package com.example.data.backend.models

import com.squareup.moshi.Json

data class SendCodeError(
    @Json (name = "code")val code: Int,
    @Json (name = "`data")val `data`: SendCodeDataError,
    @Json (name = "message")val message: Any? = null,
    @Json (name = "status")val status: String
)