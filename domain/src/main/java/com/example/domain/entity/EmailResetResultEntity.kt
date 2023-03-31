package com.example.domain.entity

sealed class EmailResetResultEntity {
    data class Success(val data: DataEmailResetEntity) : EmailResetResultEntity()
    data class Error(val error: EmailPassResetErrorsEntity) : EmailResetResultEntity()
}
