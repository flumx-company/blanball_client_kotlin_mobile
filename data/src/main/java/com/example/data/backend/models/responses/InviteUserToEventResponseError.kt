package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class InviteUserToEventResponseError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: InviteUserToEventResponseErrorData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class InviteUserToEventResponseErrorData(
    @Json(name = "errors") val errors: List<InviteUserToEventResponseErrorDetail>,
    @Json(name = "type") val type: String
)

data class InviteUserToEventResponseErrorDetail(
    @Json(name = "detail") val detail: String
)