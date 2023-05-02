package com.example.domain.entity.results

import com.example.domain.entity.responses.ResetCompleteErrorsEntity
import com.example.domain.entity.responses.DataCompleteResponseEntity

sealed class ResetCompleteResultEntity {
    data class Success(val data: DataCompleteResponseEntity) : ResetCompleteResultEntity()
    data class Error(val error: ResetCompleteErrorsEntity) : ResetCompleteResultEntity()
}
