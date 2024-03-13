package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class GetIsTechnicalWorkStatusResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetIsTechnicalWorkStatusResponseData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class GetIsTechnicalWorkStatusResponseData(
    @Json (name = "isMaintenance") val isMaintenance: Boolean
)