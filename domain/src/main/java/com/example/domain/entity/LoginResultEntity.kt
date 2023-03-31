package com.example.domain.entity

sealed class LoginResultEntity {
    data class Success(val data: LoginData) : LoginResultEntity()
    data class Error(val error: LoginErrorsDomain) : LoginResultEntity()
}