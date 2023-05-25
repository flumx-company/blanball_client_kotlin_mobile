package com.example.domain.entity.responses

data class GetUserPlannedEventsByIdErrorEntity(
    val code: Int,
    val `data`: GetUserPlannedEventsByIdErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class GetUserPlannedEventsByIdErrorDataEntity(
    val errors: List<GetUserPlannedEventsByIdDetailDataEntity>,
    val type: String
)

data class GetUserPlannedEventsByIdDetailDataEntity(
    val detail: String
)