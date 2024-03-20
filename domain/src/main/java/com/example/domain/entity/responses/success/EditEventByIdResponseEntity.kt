package com.example.domain.entity.responses.success

data class EditEventByIdResponseEntity(
    val code: Int,
    val `data`: EditEventByIdResponseEntityData,
    val message: String? = null,
    val status: String
)

data class EditEventByIdResponseEntityData(
    val success: String
)