package com.example.domain.entity.responses.errors

data class AcceptOrDiscardParticipationErrorEntity(
    val code: Int,
    val `data`: AcceptOrDiscardParticipationErrorEntityData,
    val message: String? = null,
    val status: String
)

data class AcceptOrDiscardParticipationErrorEntityData(
    val errors: List<AcceptOrDiscardParticipationErrorEntityDetail>,
    val type: String
)

data class AcceptOrDiscardParticipationErrorEntityDetail(
    val detail: String
)