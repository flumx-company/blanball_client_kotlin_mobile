package com.example.domain.entity

sealed class LoginResultEntity {
    data class Success(val data: LoginDataEntity) : LoginResultEntity()
    data class Error(val error: LoginErrorEntity) : LoginResultEntity()
}