package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class InviteUserToEventResponse(
    @Json (name = "event_id") val event_id: Int,
    @Json (name = "ids") val ids: List<Int>
)