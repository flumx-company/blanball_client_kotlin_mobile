package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class LeaveTheEventAsFunError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: LeaveTheEventAsFunErrorData,
    @Json(name = "message") val message: Any? = null,
    @Json(name = "status") val status: String
)

data class LeaveTheEventAsFunErrorData(
    @Json(name = "errors") val errors: List<LeaveTheEventAsFunDetailData>,
    @Json(name = "type") val type: String
)

data class LeaveTheEventAsFunDetailData(
    @Json(name = "detail") val detail: String
)


data class LeaveTheEventAsPlayerError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: LeaveTheEventAsPlayerErrorData,
    @Json(name = "message") val message: Any? = null,
    @Json(name = "status") val status: String
)

data class LeaveTheEventAsPlayerErrorData(
    @Json(name = "errors") val errors: List<LeaveTheEventAsPlayerDetailData>,
    @Json(name = "type") val type: String
)

data class LeaveTheEventAsPlayerDetailData(
    @Json(name = "detail") val detail: String
)