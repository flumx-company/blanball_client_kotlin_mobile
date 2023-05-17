package com.example.domain.entity.results

import com.example.domain.entity.responses.RegistrationDataEntity
import com.example.domain.entity.responses.RegistrationErrorDetailEntity

sealed class RegistrationResultEntity {
 data class Success(val data: RegistrationDataEntity) : RegistrationResultEntity()
 data class Error(val error: RegistrationErrorDetailEntity) : RegistrationResultEntity()
}