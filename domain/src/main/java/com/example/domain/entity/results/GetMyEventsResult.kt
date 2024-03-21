package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseErrorDetail
import com.example.domain.entity.responses.success.GetMyEventsResponseEntityData

sealed class GetMyEventsResult {
    data class Success(val success: GetMyEventsResponseEntityData) : GetMyEventsResult()
    data class Error (val error: GetMyEventsEntityResponseErrorDetail) : GetMyEventsResult()
}
