package com.example.data.backend.models

import com.squareup.moshi.Json

data class DataResetPassRequest(
   @Json (name = "success") val success: String
)
