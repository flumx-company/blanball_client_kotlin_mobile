package com.example.domain.entity.results

import com.example.domain.entity.responses.success.GetUkraineCitiesListResponseEntity
import com.example.domain.entity.responses.errors.GetUkraineCitiesListEntityErrorDetail

sealed class GetUkraineCitiesListResult {
    data class Success(val data: GetUkraineCitiesListResponseEntity) : GetUkraineCitiesListResult()
    data class Error(val error: GetUkraineCitiesListEntityErrorDetail) : GetUkraineCitiesListResult()
}