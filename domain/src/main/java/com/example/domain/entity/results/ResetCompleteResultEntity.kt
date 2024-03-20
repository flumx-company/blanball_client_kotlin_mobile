package com.example.domain.entity.results

import com.example.domain.entity.responses.success.ResetCompleteErrorsEntity
import com.example.domain.entity.responses.success.DataCompleteResponseEntity

sealed class ResetCompleteResultEntity {
    data class Success(val data: DataCompleteResponseEntity) : ResetCompleteResultEntity()
    data class Error(val error: ResetCompleteErrorsEntity) : ResetCompleteResultEntity()
}
