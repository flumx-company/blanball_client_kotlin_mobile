package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetPrivateRequestListResponseErrorDetailEntity
import com.example.domain.entity.responses.success.GetPrivateRequestListResponseEntity

sealed class GetPrivateEventRequestListResult {
    data class Success(val success: GetPrivateRequestListResponseEntity) : GetPrivateEventRequestListResult()
    data class Error(val error: GetPrivateRequestListResponseErrorDetailEntity) : GetPrivateEventRequestListResult()
}