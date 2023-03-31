package com.example.data.backend.models

import com.squareup.moshi.Json

data class EmailPassResetError(
   @Json (name = "code") val code: Int,
   @Json (name = "`data`") val `data`: EmailPassDataError,
   @Json (name = "message") val message: Any,
   @Json (name = "status") val status: String
)