package com.example.domain.entity.results

import com.example.domain.entity.responses.success.ResetCompleteErrorsEntity
import com.example.domain.entity.responses.success.DataCompleteResponseEntity

sealed class ResetCompleteResult {
    data class Success(val data: DataCompleteResponseEntity) : ResetCompleteResult()
    data class Error(val error: ResetCompleteErrorsEntity) : ResetCompleteResult()
}
