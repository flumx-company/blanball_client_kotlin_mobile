package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.GetIsTechnicalWorkStatusErrorDetailEntity
import com.example.domain.entity.responses.success.GetIsTechnicalWorkStatusResponseEntityData

sealed class GetIsTechnicalWorkStatusResult {
    data class Success(val data: GetIsTechnicalWorkStatusResponseEntityData): GetIsTechnicalWorkStatusResult()
    data class Error(val error: GetIsTechnicalWorkStatusErrorDetailEntity): GetIsTechnicalWorkStatusResult()
}
