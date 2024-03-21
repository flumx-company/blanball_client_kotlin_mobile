package com.example.domain.entity.responses.errors

data class LeaveTheEventAsFunErrorEntity(
    val code: Int,
    val `data`: LeaveTheEventAsFunErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class LeaveTheEventAsFunErrorDataEntity(
    val errors: List<LeaveTheEventAsFunDetailDataEntity>,
    val type: String
)

data class LeaveTheEventAsFunDetailDataEntity(
    val detail: String
)


data class LeaveTheEventAsPlayerErrorEntity(
    val code: Int,
    val `data`: LeaveTheEventAsPlayerErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class LeaveTheEventAsPlayerErrorDataEntity(
    val errors: List<LeaveTheEventAsPlayerDetailDataEntity>,
    val type: String
)

data class LeaveTheEventAsPlayerDetailDataEntity(
    val detail: String
)