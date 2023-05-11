package com.example.domain.entity.results

import com.example.domain.entity.responses.DataEmailResetEntity
import com.example.domain.entity.responses.EmailPassResetErrorsEntity

sealed class EmailResetResultEntity {
    data class Success(val data: DataEmailResetEntity) : EmailResetResultEntity()
    data class Error(val error: EmailPassResetErrorsEntity) : EmailResetResultEntity()
}
