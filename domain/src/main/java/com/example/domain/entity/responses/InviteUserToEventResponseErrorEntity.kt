package com.example.domain.entity.responses

data class InviteUserToEventResponseErrorEntity(
    val code: Int,
    val `data`: InviteUserToEventResponseErrorDataEntity,
    val message: String? = null,
    val status: String
)

data class InviteUserToEventResponseErrorDataEntity(
    val errors: List<InviteUserToEventResponseErrorDetailEntity>,
    val type: String
)

data class InviteUserToEventResponseErrorDetailEntity(
    val detail: String
)
