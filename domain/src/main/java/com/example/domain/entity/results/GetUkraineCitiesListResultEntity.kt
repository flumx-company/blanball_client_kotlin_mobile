package com.example.domain.entity.results

import com.example.domain.entity.responses.GetUkraineCitiesListResponseEntity
import com.example.domain.entity.responses.errors.GetUkraineCitiesListEntityErrorDetail

sealed class GetUkraineCitiesListResultEntity {
    data class Success(val data: GetUkraineCitiesListResponseEntity) : GetUkraineCitiesListResultEntity()
    data class Error(val error: GetUkraineCitiesListEntityErrorDetail) : GetUkraineCitiesListResultEntity()
}