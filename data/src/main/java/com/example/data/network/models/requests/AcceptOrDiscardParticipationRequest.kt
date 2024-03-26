package com.example.data.network.models.requests

data class AcceptOrDiscardParticipationRequest(
    val ids: List<Int>,
    val type: Boolean
)