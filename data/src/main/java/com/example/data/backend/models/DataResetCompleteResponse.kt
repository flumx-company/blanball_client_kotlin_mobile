package com.example.data.backend.models

import com.squareup.moshi.Json

data class DataResetCompleteResponse(
   @Json (name = "success") val success: String
)