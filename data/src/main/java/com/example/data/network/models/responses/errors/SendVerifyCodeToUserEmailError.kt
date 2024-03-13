package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class SendVerifyCodeToUserEmailError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: SendVerifyCodeToUserEmailErrorData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class SendVerifyCodeToUserEmailErrorData(
    @Json (name = "errors") val errors: List<SendVerifyCodeToUserEmailErrorDataDetail>,
    @Json (name = "type") val type: String
)

data class SendVerifyCodeToUserEmailErrorDataDetail(
    @Json (name = "detail") val detail: String
)