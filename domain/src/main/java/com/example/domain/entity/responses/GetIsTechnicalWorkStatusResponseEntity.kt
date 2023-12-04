package com.example.domain.entity.responses

data class GetIsTechnicalWorkStatusResponseEntity(
    val code: Int,
    val `data`: GetIsTechnicalWorkStatusResponseEntityData,
    val message: String? = null,
    val status: String
)

data class GetIsTechnicalWorkStatusResponseEntityData(
    val isMaintenance: Boolean
)