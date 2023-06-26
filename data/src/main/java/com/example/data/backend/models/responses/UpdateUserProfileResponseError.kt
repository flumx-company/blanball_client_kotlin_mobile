package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class UpdateUserProfileResponseError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: UpdateUserProfileResponseErrorData,
    @Json (name = "message") val message: String? = null,
    @Json (name = "status") val status: String
)

data class UpdateUserProfileResponseErrorData(
    @Json (name = "errors") val errors: List<UpdateUserProfileResponseErrorDetail>,
    @Json (name = "type") val type: String
)

data class UpdateUserProfileResponseErrorDetail(
    @Json (name = "detail") val detail: String
)