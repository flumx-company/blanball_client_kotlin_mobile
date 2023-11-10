package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetMyEventsResponseError(
    @Json (name = "code" ) val code: Int,
    @Json(name = "`data`" ) val `data`: GetMyEventsResponseErrorData,
    @Json(name = "message" ) val message: String? = null,
    @Json(name = "status" ) val status: String
)

data class GetMyEventsResponseErrorData(
    @Json(name = "errors" ) val errors: List<GetMyEventsResponseErrorDetail>,
    @Json(name = "type" ) val type: String
)

data class GetMyEventsResponseErrorDetail(
    @Json(name = "detail" ) val detail: String
)