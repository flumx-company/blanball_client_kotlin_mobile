package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json


data class JoinToEventAsFunError(
    @Json(name ="code") val code: Int,
    @Json(name ="`data`") val `data`: JoinToEventAsFunErrorData,
    @Json(name ="message") val message: Any? = null,
    @Json(name ="status") val status: String
)

data class JoinToEventAsFunErrorData(
    @Json(name ="errors") val errors: List<JoinToEventAsFunDetailData>,
    @Json(name ="type") val type: String
)

data class JoinToEventAsFunDetailData(
    @Json(name ="detail") val detail: String
)

data class JoinToEventAsPlayerError(
    @Json(name ="code") val code: Int,
    @Json(name ="`data`") val `data`: JoinToEventAsPlayerErrorData,
    @Json(name ="message") val message: Any? = null,
    @Json(name ="status") val status: String
)

data class JoinToEventAsPlayerErrorData(
    @Json(name ="errors") val errors: List<JoinToEventAsPlayerDetailData>,
    @Json(name ="type") val type: String
)

data class JoinToEventAsPlayerDetailData(
    @Json(name ="detail") val detail: String
)