package com.example.data.network.models.responses.errors

import com.squareup.moshi.Json

data class GetPrivateRequestListResponseError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetPrivateRequestListResponseErrorData,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class GetPrivateRequestListResponseErrorData(
    @Json (name = "errors") val errors: List<GetPrivateRequestListResponseErrorDetail>,
    @Json (name = "type") val type: String
)

data class GetPrivateRequestListResponseErrorDetail(
    @Json (name = "detail") val detail: String
)