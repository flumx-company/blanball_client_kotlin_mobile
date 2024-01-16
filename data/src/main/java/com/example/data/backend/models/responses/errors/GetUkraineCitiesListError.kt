package com.example.data.backend.models.responses.errors

data class GetUkraineCitiesListError(
    val code: Int,
    val `data`: GetUkraineCitiesListDataError,
    val message: Any? = null,
    val status: String
)

data class GetUkraineCitiesListDataError(
    val errors: List<GetUkraineCitiesListErrorDetail>,
    val type: String
)

data class GetUkraineCitiesListErrorDetail(
    val detail: String
)