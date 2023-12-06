package com.example.domain.entity.responses.errors

data class EditEventByIdResponseErrorEntity(
    val code: Int,
    val `data`: EditEventByIdResponseErrorDataEntity,
    val message: String? = null,
    val status: String
)

data class EditEventByIdResponseErrorDataEntity(
    val errors: List<EditEventByIdResponseErrorDetailEntity>,
    val type: String
)

data class EditEventByIdResponseErrorDetailEntity(
    val detail: String
)