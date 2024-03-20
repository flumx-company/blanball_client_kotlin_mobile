package com.example.domain.entity.responses.success

data class GetUkraineCitiesListResponseEntity(
    val code: Int,
    val `data`: List<GetUkraineCitiesListResponseEntityData>,
    val message: Any? = null,
    val status: String
)

data class GetUkraineCitiesListResponseEntityData(
    val cities: List<GetUkraineCitiesListResponseEntityItem>,
    val name: String
)

data class GetUkraineCitiesListResponseEntityItem(
    val lat: String,
    val lng: String,
    val name: String
)