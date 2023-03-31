package com.example.data.backend.models

import com.squareup.moshi.Json

data class EmailPassDataError(
   @Json (name = "errors") val errors: List<EmailPassResetErrors>,
   @Json (name = "type") val type: String
)