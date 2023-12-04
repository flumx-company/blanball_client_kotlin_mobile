package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class PostEmailVerifyCodeError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: PostEmailVerifyCodeDataError,
    @Json(name = "message") val message: Any? = null,
    @Json(name = "status") val status: String
)

data class PostEmailVerifyCodeDataError(
    @Json(name = "errors") val errors: List<PostEmailVerifyCodeErrorDetail>,
    @Json(name = "type") val type: String
)

data class PostEmailVerifyCodeErrorDetail(
    @Json(name = "detail") val detail: String
)