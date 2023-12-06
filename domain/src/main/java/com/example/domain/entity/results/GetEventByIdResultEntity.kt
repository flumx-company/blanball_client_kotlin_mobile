package com.example.domain.entity.results

import com.example.domain.entity.responses.GetEventByIdResponseDataEntity
import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorDetailEntity

sealed class GetEventByIdResultEntity {
    data class Success(val data: GetEventByIdResponseDataEntity) : GetEventByIdResultEntity()
    data class Error(val error: GetEventByIdResponseErrorDetailEntity) : GetEventByIdResultEntity()
}
