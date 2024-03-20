package com.example.data.network.models.responses.errors

data class EditEventByIdResponseError(
    val code: Int,
    val `data`: EditEventByIdResponseErrorData,
    val message: String? = null,
    val status: String
)

data class EditEventByIdResponseErrorData(
    val errors: List<EditEventByIdResponseErrorDetail>,
    val type: String
)

data class EditEventByIdResponseErrorDetail(
    val detail: String
)