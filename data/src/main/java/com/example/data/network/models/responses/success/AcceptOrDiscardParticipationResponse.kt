package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class AcceptOrDiscardParticipationResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: List<Any>,
    @Json(name = "message") val message: Any,
    @Json(name = "status") val status: String
)