package com.example.data.backend.models

import com.squareup.moshi.Json

data class Error(
   @Json (name = "detail") val detail: String
)