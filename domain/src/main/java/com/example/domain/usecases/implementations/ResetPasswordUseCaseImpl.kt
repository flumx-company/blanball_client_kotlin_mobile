package com.example.domain.usecases.implementations

import com.example.domain.entity.results.EmailResetResult
import com.example.domain.entity.results.ResetCompleteResult
import com.example.domain.entity.results.SendCodeResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.ResetPasswordUseCase
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : ResetPasswordUseCase {
    override suspend fun executeSendEmailPassReset(email: String): EmailResetResult {
        return appRepository.sendEmailPassReset(email)
    }

    override suspend fun executeSendCode(code: String): SendCodeResult {
        return appRepository.sendCode(code)
    }

    override suspend fun executeChangePassword(newPassword: String): ResetCompleteResult {
        return appRepository.changePassword(newPassword)
    }
}