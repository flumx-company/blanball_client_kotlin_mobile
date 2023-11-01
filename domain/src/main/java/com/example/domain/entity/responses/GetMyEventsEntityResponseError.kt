package com.example.domain.entity.responses

data class GetMyEventsEntityResponseError(
    val code: Int,
    val `data`: GetMyEventsEntityResponseErrorData,
    val message: String? = null,
    val status: String
)

data class GetMyEventsEntityResponseErrorData(
    val errors: List<GetMyEventsEntityResponseErrorDetail>,
    val type: String
)

data class GetMyEventsEntityResponseErrorDetail(
    val detail: String
)