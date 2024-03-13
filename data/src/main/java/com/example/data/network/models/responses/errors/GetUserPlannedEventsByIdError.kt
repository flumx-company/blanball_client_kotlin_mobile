package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class GetUserPlannedEventsByIdError (
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetUserPlannedEventsByIdErrorData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class GetUserPlannedEventsByIdErrorData (
    @Json (name = "errors") val errors: List<GetUserPlannedEventsByIdDetailData>,
    @Json (name = "type") val type: String
)

data class GetUserPlannedEventsByIdDetailData(
    @Json (name = "detail") val detail: String
)