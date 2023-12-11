package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetMyEventsEntityResponseErrorDetail
import com.example.domain.entity.responses.GetMyEventsResponseEntityData

sealed class GetMyEventsResultEntity {
    data class Success(val success: GetMyEventsResponseEntityData) : GetMyEventsResultEntity()
    data class Error (val error: GetMyEventsEntityResponseErrorDetail) : GetMyEventsResultEntity()
}
