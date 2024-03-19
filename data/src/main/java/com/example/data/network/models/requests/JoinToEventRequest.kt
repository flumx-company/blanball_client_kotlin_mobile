package com.example.data.network.models.requests

import com.squareup.moshi.Json

data class JoinToEventAsPlayerRequest(
   @Json (name = "event_id") val event_id: Int
)
data class JoinToEventAsFunRequest(
   @Json (name = "event_id") val event_id: Int
)