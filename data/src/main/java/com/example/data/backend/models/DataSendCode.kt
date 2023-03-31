package com.example.data.backend.models

import com.squareup.moshi.Json

data class DataSendCode(
    @Json (name = "success") val success: String
)