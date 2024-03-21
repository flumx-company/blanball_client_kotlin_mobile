package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetEventByIdResponseErrorDetailEntity
import com.example.domain.entity.responses.success.GetEventByIdResponseDataEntity

sealed class GetEventByIdResult {
    data class Success(val data: GetEventByIdResponseDataEntity) : GetEventByIdResult()
    data class Error(val error: GetEventByIdResponseErrorDetailEntity) : GetEventByIdResult()
}
