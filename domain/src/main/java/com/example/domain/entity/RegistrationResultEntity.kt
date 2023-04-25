package com.example.domain.entity


sealed class RegistrationResultEntity {
 data class Success(val data: RegistrationDataEntity) : RegistrationResultEntity()
 data class Error(val error: RegistrationErrorDetailEntity) : RegistrationResultEntity()
}
