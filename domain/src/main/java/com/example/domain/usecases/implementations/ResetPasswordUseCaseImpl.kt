package com.example.domain.usecases.implementations

import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.RegistrationResultEntity
import com.example.domain.entity.ResetCompleteResultEntity
import com.example.domain.entity.SendCodeResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.ResetPasswordUseCase
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : ResetPasswordUseCase {
    override suspend fun executeSendEmailPassReset(email: String): EmailResetResultEntity {
        return appRepository.sendEmailPassReset(email)
    }

    override suspend fun executeSendCode(code: String): SendCodeResultEntity {
        return appRepository.sendCode(code)
    }

    override suspend fun executeChangePassword(newPassword: String): ResetCompleteResultEntity {
        return appRepository.changePassword(newPassword)
    }

    override suspend fun executeRegistration(
        email: String,
        phone: String,
        password: String,
        re_password: String,
        name: String,
        lastName: String,
        gender: String
    ): RegistrationResultEntity {
        return appRepository.registration(email = email, phone = password, re_password = re_password, name = name, lastName = lastName, gender = gender, password = password)
    }
}