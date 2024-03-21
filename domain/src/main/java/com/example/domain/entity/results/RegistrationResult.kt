package com.example.domain.entity.results

import com.example.domain.entity.responses.success.RegistrationDataEntity
import com.example.domain.entity.responses.errors.RegistrationErrorDetailEntity

sealed class RegistrationResult {
 data class Success(val data: RegistrationDataEntity) : RegistrationResult()
 data class Error(val error: RegistrationErrorDetailEntity) : RegistrationResult()
}
