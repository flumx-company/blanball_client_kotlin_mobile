package com.example.domain.entity.responses

data class GetEventByIdResponseErrorEntity(
    val code: Int,
    val `data`: GetEventByIdResponseErrorDataEntity,
    val message: String? = null,
    val status: String
)

data class GetEventByIdResponseErrorDataEntity(
    val errors: List<GetEventByIdResponseErrorDetailEntity>,
    val type: String
)

data class GetEventByIdResponseErrorDetailEntity(
    val detail: String
)