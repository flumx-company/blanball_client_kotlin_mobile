package com.example.domain.entity


sealed class RegistrationResultEntity {
 data class Success(val data: RegistrationResponseEntity) : RegistrationResultEntity()
 data class Error(val error: RegistrationErrorEntity) : RegistrationResultEntity()
}
