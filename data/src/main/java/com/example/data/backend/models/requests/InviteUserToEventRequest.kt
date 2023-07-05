package com.example.data.backend.models.requests

import com.squareup.moshi.Json

data class InviteUserToEventRequest(
    @Json (name = "event_id") val event_id: Int,
    @Json (name = "ids") val ids: List<Int>
)