package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class JoinToEventAsPlayerResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: JoinToEventAsPlayerResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class JoinToEventAsPlayerResponseData(
    @Json(name = "success") val success: String
)

data class JoinToEventAsFunResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: JoinToEventAsFunResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class JoinToEventAsFunResponseData(
    @Json(name = "success") val success: String
)