package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetUkraineCitiesListEntityErrorData
import com.example.domain.entity.responses.errors.GetUkraineCitiesListEntityErrorDetail

sealed class GetUkraineCitiesListResultEntity {
    data class Success(val data: GetUkraineCitiesListEntityErrorData) : GetUkraineCitiesListResultEntity()
    data class Error(val error: GetUkraineCitiesListEntityErrorDetail) : GetUkraineCitiesListResultEntity()
}