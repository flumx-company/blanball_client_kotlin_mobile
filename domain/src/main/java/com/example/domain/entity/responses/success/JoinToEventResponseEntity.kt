package com.example.domain.entity.responses.success

data class JoinToEventAsPlayerResponseEntity(
    val code: Int,
    val `data`: JoinToEventAsPlayerResponseEntityData,
    val message: String? = null,
    val status: String
)

data class JoinToEventAsPlayerResponseEntityData(
    val success: String
)

data class JoinToEventAsFunResponseEntity(
    val code: Int,
    val `data`: JoinToEventAsFunResponseEntityData,
    val message: String? = null,
    val status: String
)

data class JoinToEventAsFunResponseEntityData(
    val success: String
)