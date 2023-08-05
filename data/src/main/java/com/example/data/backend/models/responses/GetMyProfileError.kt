package com.example.data.backend.models.responses

import com.squareup.moshi.Json

data class GetMyProfileError(
   @Json (name = "code") val code: Int,
   @Json (name = "`data`") val `data`: GetMyProfileErrorData,
   @Json (name = "message") val message: String,
   @Json (name = "status") val status: String
)

data class GetMyProfileErrorData(
   @Json (name = "errors") val errors: List<GetMyProfileErrorDetail>,
   @Json (name = "type") val type: String
)

data class GetMyProfileErrorDetail(
   @Json (name = "detail") val detail: String
)