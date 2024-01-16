package com.example.data.backend.models.responses.success

import com.squareup.moshi.Json

data class GetUkraineCitiesListResponse(
    @Json (name ="code") val code: Int,
    @Json (name ="`data`") val `data`: List<GetUkraineCitiesListResponseData>,
    @Json (name ="message") val message: Any? = null,
    @Json (name ="status") val status: String
)

data class GetUkraineCitiesListResponseData(
    @Json (name ="cities") val cities: List<GetUkraineCitiesListResponseItem>,
    @Json (name ="name") val name: String
)

data class GetUkraineCitiesListResponseItem(
    @Json (name ="lat") val lat: String,
    @Json (name ="lng") val lng: String,
    @Json (name ="name") val name: String
)