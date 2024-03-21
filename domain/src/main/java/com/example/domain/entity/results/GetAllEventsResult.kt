package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetAllEventEntityResponseErrorDetail
import com.example.domain.entity.responses.success.GetAllEventResponseEntityData

sealed class GetAllEventsResult {
    data class Success(val success: GetAllEventResponseEntityData) : GetAllEventsResult()
    data class Error (val error: GetAllEventEntityResponseErrorDetail) : GetAllEventsResult()
}
