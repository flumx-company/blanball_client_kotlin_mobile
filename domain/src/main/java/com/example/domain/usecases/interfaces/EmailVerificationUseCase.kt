package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.PostEmailVerifyCodeResult
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResult

interface EmailVerificationUseCase {
    suspend fun executeSendVerifyCodeToUserEmail(
        page: Int
    ): SendVerifyCodeToUserEmailResult

    suspend fun executePostEmailVerifyCode(
        code: String
    ) : PostEmailVerifyCodeResult
}