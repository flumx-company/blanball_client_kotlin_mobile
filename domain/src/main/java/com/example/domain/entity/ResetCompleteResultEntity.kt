package com.example.domain.entity

sealed class ResetCompleteResultEntity {
    data class Success(val data: DataCompleteResponseEntity) : ResetCompleteResultEntity()
    data class Error(val error: ResetCompleteErrorsEntity) : ResetCompleteResultEntity()
}
