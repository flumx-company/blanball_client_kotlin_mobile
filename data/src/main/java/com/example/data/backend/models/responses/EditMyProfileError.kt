package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class EditMyProfileError(
    @Json(name = "code") val code: Int,
    @Json(name = "`data`") val `data`: EditMyProfileErrorData,
    @Json(name = "message") val message: Any,
    @Json(name = "status") val status: String
)

data class EditMyProfileErrorData(
    @Json(name = "errors") val errors: List<EditMyProfileErrorDetail>,
    @Json(name = "type") val type: String
)

data class EditMyProfileErrorDetail(
    @Json(name = "detail") val detail: String
)