package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.LoginResultEntity

interface UserLoginUseCase {
    suspend fun executeUserLogin (email: String, password: String): LoginResultEntity
}