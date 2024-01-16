package com.example.domain.entity.responses.errors

data class GetUkraineCitiesListErrorEntity(
    val code: Int,
    val `data`: GetUkraineCitiesListEntityErrorData,
    val message: Any? = null,
    val status: String
)

data class GetUkraineCitiesListEntityErrorData(
    val errors: List<GetUkraineCitiesListEntityErrorDetail>,
    val type: String
)

data class GetUkraineCitiesListEntityErrorDetail(
    val detail: String
)