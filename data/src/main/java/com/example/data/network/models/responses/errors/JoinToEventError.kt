package com.example.data.network.models.responses.errors


data class JoinToEventAsFunError(
    val code: Int,
    val `data`: JoinToEventAsFunErrorData,
    val message: Any? = null,
    val status: String
)

data class JoinToEventAsFunErrorData(
    val errors: List<JoinToEventAsFunDetailData>,
    val type: String
)

data class JoinToEventAsFunDetailData(
    val detail: String
)

data class JoinToEventAsPlayerError(
    val code: Int,
    val `data`: JoinToEventAsPlayerErrorData,
    val message: Any? = null,
    val status: String
)

data class JoinToEventAsPlayerErrorData(
    val errors: List<JoinToEventAsPlayerDetailData>,
    val type: String
)

data class JoinToEventAsPlayerDetailData(
    val detail: String
)