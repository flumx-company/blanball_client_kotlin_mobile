package com.example.domain.entity.results

import com.example.domain.entity.responses.success.DataEmailResetEntity
import com.example.domain.entity.responses.success.EmailPassResetErrorsEntity

sealed class EmailResetResult {
    data class Success(val data: DataEmailResetEntity) : EmailResetResult()
    data class Error(val error: EmailPassResetErrorsEntity) : EmailResetResult()
}
