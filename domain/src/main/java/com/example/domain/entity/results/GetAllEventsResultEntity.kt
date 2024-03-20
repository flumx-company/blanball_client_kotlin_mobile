package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetAllEventEntityResponseErrorDetail
import com.example.domain.entity.responses.success.GetAllEventResponseEntityData

sealed class GetAllEventsResultEntity {
    data class Success(val success: GetAllEventResponseEntityData) : GetAllEventsResultEntity()
    data class Error (val error: GetAllEventEntityResponseErrorDetail) : GetAllEventsResultEntity()
}
