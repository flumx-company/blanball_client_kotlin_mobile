package com.example.domain.usecases.interfaces

import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.RegistrationResultEntity
import com.example.domain.entity.ResetCompleteResultEntity
import com.example.domain.entity.SendCodeResultEntity

interface ResetPasswordUseCase {

    suspend fun executeSendEmailPassReset(email: String): EmailResetResultEntity
    suspend fun executeSendCode(code: String): SendCodeResultEntity
    suspend fun executeChangePassword(newPassword: String): ResetCompleteResultEntity
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