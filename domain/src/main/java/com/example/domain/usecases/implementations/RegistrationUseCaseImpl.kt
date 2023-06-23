package com.example.domain.usecases.implementations

import com.example.domain.entity.results.RegistrationResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.RegistrationUseCase

class RegistrationUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : RegistrationUseCase {
    override suspend fun executeRegistration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String
    ): RegistrationResultEntity {
        return appRepository.registration(email = email, phone = phone, re_password = re_password, name = name, lastName = lastName, gender = gender, password = password)
    }
}