package com.example.data.network.models.requests

import com.squareup.moshi.Json


data class LeaveTheEventAsPlayerRequest(
    @Json(name = "event_id") val event_id: Int
)
data class LeaveTheEventAsFanRequest(
    @Json(name = "event_id") val event_id: Int
)