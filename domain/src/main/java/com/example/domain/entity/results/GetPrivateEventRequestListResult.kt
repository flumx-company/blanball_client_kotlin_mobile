package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetPrivateRequestListResponseErrorDetailEntity
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntityData

sealed class GetPrivateEventRequestListResult {
    data class Success(val data: GetPrivateRequestListResponseEntityData) : GetPrivateEventRequestListResult()
    data class Error(val error: GetPrivateRequestListResponseErrorDetailEntity) : GetPrivateEventRequestListResult()
}