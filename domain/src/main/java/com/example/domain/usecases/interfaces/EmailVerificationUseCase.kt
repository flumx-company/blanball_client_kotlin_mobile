package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.PostEmailVerifyCodeResultEntity
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResultEntity

interface EmailVerificationUseCase {
    suspend fun executeSendVerifyCodeToUserEmail(
        page: Int
    ): SendVerifyCodeToUserEmailResultEntity

    suspend fun executePostEmailVerifyCode(
        code: String
    ) : PostEmailVerifyCodeResultEntity
}