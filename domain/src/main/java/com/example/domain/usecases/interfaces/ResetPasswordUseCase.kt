package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.EmailResetResult
import com.example.domain.entity.results.ResetCompleteResult
import com.example.domain.entity.results.SendCodeResult

interface ResetPasswordUseCase {

    suspend fun executeSendEmailPassReset(email: String): EmailResetResult
    suspend fun executeSendCode(code: String): SendCodeResult
    suspend fun executeChangePassword(newPassword: String): ResetCompleteResult
}