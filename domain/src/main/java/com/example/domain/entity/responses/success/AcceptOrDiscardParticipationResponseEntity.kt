package com.example.domain.entity.responses.success

data class AcceptOrDiscardParticipationResponseEntity(
    val code: Int,
    val `data`: List<Any>,
    val message: Any,
    val status: String
)