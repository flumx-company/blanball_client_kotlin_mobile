package com.example.domain.entity.results

import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdDataResponseEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdDetailDataEntity

sealed class GetUserPlannedEventsByIdResultEntity {
    data class Success(val data: GetUserPlannedEventsByIdDataResponseEntity) : GetUserPlannedEventsByIdResultEntity()
    data class Error(val error: GetUserPlannedEventsByIdDetailDataEntity) : GetUserPlannedEventsByIdResultEntity()
}
