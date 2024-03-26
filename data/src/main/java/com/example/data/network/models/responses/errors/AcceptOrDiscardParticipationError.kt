package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class AcceptOrDiscardParticipationError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: AcceptOrDiscardParticipationErrorData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class AcceptOrDiscardParticipationErrorData(
    @Json(name = "errors") val errors: List<AcceptOrDiscardParticipationErrorDetail>,
    @Json(name = "type") val type: String
)

data class AcceptOrDiscardParticipationErrorDetail(
    @Json(name = "detail") val detail: String
)