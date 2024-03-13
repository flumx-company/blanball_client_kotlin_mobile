package com.example.data.network.models.responses.success

data class JoinToEventAsPlayerResponse(
    val code: Int,
    val `data`: JoinToEventAsPlayerResponseData,
    val message: String? = null,
    val status: String
)

data class JoinToEventAsPlayerResponseData(
    val success: String
)

data class JoinToEventAsFunResponse(
    val code: Int,
    val `data`: JoinToEventAsFunResponseData,
    val message: String? = null,
    val status: String
)

data class JoinToEventAsFunResponseData(
    val success: String
)