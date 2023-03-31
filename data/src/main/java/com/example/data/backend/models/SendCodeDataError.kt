package com.example.data.backend.models

import com.squareup.moshi.Json

data class SendCodeDataError(
   @Json (name = "errors") val errors: List<SendCodeErrors>,
   @Json (name = "type") val type: String
)