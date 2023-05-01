package com.example.domain.usecases.interfaces

import com.example.domain.entity.RegistrationResultEntity

interface RegistrationUseCase {
    suspend fun executeRegistration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String
    ): RegistrationResultEntity
}