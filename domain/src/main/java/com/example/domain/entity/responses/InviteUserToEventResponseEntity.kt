package com.example.domain.entity.responses

data class InviteUserToEventResponseEntity(
    val event_id: Int,
    val ids: List<Int>
)