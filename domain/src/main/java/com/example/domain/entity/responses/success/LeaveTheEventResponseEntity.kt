package com.example.domain.entity.responses.success

data class LeaveTheEventAsPlayerResponseEntity(
    val code: Int,
    val `data`: LeaveTheEventAsPlayerResponseEntityData,
    val message: String? = null,
    val status: String
)

data class LeaveTheEventAsPlayerResponseEntityData(
    val success: String
)

data class LeaveTheEventAsFanResponseEntity(
    val code: Int,
    val `data`: LeaveTheEventAsFanResponseEntityData,
    val message: String? = null,
    val status: String
)

data class LeaveTheEventAsFanResponseEntityData(
    val success: String
)