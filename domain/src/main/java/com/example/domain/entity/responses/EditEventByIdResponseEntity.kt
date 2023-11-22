package com.example.domain.entity.responses

data class EditEventByIdResponseEntity(
    val code: Int,
    val `data`: EditEventByIdResponseEntityData,
    val message: Any,
    val status: String
)

data class EditEventByIdResponseEntityData(
    val success: String
)