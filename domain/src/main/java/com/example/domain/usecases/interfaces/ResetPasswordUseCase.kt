package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.EmailResetResultEntity
import com.example.domain.entity.results.ResetCompleteResultEntity
import com.example.domain.entity.results.SendCodeResultEntity

interface ResetPasswordUseCase {

    suspend fun executeSendEmailPassReset(email: String): EmailResetResultEntity
    suspend fun executeSendCode(code: String): SendCodeResultEntity
    suspend fun executeChangePassword(newPassword: String): ResetCompleteResultEntity
}