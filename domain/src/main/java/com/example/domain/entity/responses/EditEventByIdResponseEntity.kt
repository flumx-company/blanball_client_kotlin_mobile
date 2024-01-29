package com.example.domain.entity.responses

data class EditEventByIdResponseEntity(
    val code: Int,
    val `data`: EditEventByIdResponseEntityData,
    val message: String? = null,
    val status: String
)

data class EditEventByIdResponseEntityData(
    val success: String
)