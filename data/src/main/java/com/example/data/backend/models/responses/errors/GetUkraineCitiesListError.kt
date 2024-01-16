package com.example.data.backend.models.responses.errors

import com.squareup.moshi.Json

data class GetUkraineCitiesListError(
    @Json (name = "code") val code: Int,
    @Json (name = "`data`") val `data`: GetUkraineCitiesListDataError,
    @Json (name = "message") val message: Any? = null,
    @Json (name = "status") val status: String
)

data class GetUkraineCitiesListDataError(
    @Json (name = "errors") val errors: List<GetUkraineCitiesListErrorDetail>,
    @Json (name = "type") val type: String
)

data class GetUkraineCitiesListErrorDetail(
    @Json (name = "detail") val detail: String
)