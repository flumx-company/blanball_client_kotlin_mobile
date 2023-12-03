package com.example.domain.entity.results

import com.example.domain.entity.responses.GetIsTechnicalWorkStatusErrorDetailEntity
import com.example.domain.entity.responses.GetIsTechnicalWorkStatusResponseEntity
import com.example.domain.entity.responses.GetIsTechnicalWorkStatusResponseEntityData

sealed class GetIsTechnicalWorkStatusResultEntity {
    data class Success(val data: GetIsTechnicalWorkStatusResponseEntityData): GetIsTechnicalWorkStatusResultEntity()
    data class Error(val error: GetIsTechnicalWorkStatusErrorDetailEntity): GetIsTechnicalWorkStatusResultEntity()
}
