package com.example.data.backend.models

import com.squareup.moshi.Json

data class ResetCompleteErrors(
   @Json (name = "detail") val detail: String
)