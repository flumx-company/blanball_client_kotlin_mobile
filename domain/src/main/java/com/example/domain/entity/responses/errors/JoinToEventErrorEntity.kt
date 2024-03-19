package com.example.domain.entity.responses.errors

data class JoinToEventAsFunErrorEntity(
    val code: Int,
    val `data`: JoinToEventAsFunErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class JoinToEventAsFunErrorDataEntity(
    val errors: List<JoinToEventAsFunDetailDataEntity>,
    val type: String
)

data class JoinToEventAsFunDetailDataEntity(
    val detail: String
)


data class JoinToEventAsPlayerErrorEntity(
    val code: Int,
    val `data`: JoinToEventAsPlayerErrorDataEntity,
    val message: Any? = null,
    val status: String
)

data class JoinToEventAsPlayerErrorDataEntity(
    val errors: List<JoinToEventAsPlayerDetailDataEntity>,
    val type: String
)

data class JoinToEventAsPlayerDetailDataEntity(
    val detail: String
)