package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class LeaveTheEventAsPlayerResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: LeaveTheEventAsPlayerResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class LeaveTheEventAsPlayerResponseData(
    @Json(name = "success") val success: String
)

data class LeaveTheEventAsFanResponse(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: LeaveTheEventAsFanResponseData,
    @Json(name = "message") val message: String? = null,
    @Json(name = "status") val status: String
)

data class LeaveTheEventAsFanResponseData(
    @Json(name = "success") val success: String
)