package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class ResetCompleteResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: DataResetCompleteResponse,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class DataResetCompleteResponse(
   @Json(name = "success") val success: String
)