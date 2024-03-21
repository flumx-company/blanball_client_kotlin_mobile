package com.example.domain.entity.results

import com.example.domain.entity.responses.success.GetUserPlannedEventsByIdDataResponseEntity
import com.example.domain.entity.responses.errors.GetUserPlannedEventsByIdDetailDataEntity

sealed class GetUserPlannedEventsByIdResult {
    data class Success(val data: GetUserPlannedEventsByIdDataResponseEntity) : GetUserPlannedEventsByIdResult()
    data class Error(val error: GetUserPlannedEventsByIdDetailDataEntity) : GetUserPlannedEventsByIdResult()
}
