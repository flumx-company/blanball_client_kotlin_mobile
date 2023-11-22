package com.example.data.backend.models.responses

data class EditEventByIdResponse(
    val code: Int,
    val `data`: EditEventByIdResponseData,
    val message: Any,
    val status: String
)

data class EditEventByIdResponseData(
    val success: String
)