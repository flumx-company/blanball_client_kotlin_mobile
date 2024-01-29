package com.example.data.backend.models.responses.success

data class EditEventByIdResponse(
    val code: Int,
    val `data`: EditEventByIdResponseData,
    val message: String? = null,
    val status: String
)

data class EditEventByIdResponseData(
    val success: String
)