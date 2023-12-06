package com.example.domain.entity.responses.errors

data class GetAllEventEntityResponseError(
    val code: Int,
    val `data`: GetAllEventEntityResponseErrorData,
    val message: String? = null,
    val status: String
)

data class GetAllEventEntityResponseErrorData(
    val errors: List<GetAllEventEntityResponseErrorDetail>,
    val type: String
)

data class GetAllEventEntityResponseErrorDetail(
    val detail: String
)