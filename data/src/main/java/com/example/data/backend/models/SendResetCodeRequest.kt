package com.example.data.backend.models

import com.squareup.moshi.Json

data class SendResetCodeRequest(
   @Json (name = "verify_code") val verify_code: String
)