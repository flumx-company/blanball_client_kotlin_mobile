package com.example.domain.entity.results

import com.example.domain.entity.responses.success.RegistrationDataEntity
import com.example.domain.entity.responses.errors.RegistrationErrorDetailEntity

sealed class RegistrationResultEntity {
 data class Success(val data: RegistrationDataEntity) : RegistrationResultEntity()
 data class Error(val error: RegistrationErrorDetailEntity) : RegistrationResultEntity()
}
