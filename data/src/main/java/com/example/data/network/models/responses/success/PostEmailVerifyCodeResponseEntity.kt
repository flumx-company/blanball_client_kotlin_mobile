package com.example.data.network.models.responses.success

import com.squareup.moshi.Json

data class PostEmailVerifyCodeResponse(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: PostEmailVerifyCodeResponseData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class PostEmailVerifyCodeResponseData(
    @Json (name = "success") val success: String
)