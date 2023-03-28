package com.example.domain.entity

sealed class LoginResultEntity {
    data class Success(val data: ) : LoginResultEntity()
    data class Error(val error: ) : LoginResultEntity()
}