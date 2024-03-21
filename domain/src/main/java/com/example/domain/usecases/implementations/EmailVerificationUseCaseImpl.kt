package com.example.domain.usecases.implementations

import com.example.domain.entity.results.PostEmailVerifyCodeResult
import com.example.domain.entity.results.SendVerifyCodeToUserEmailResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.EmailVerificationUseCase
import javax.inject.Inject

class EmailVerificationUseCaseImpl @Inject
constructor(
    internal val appRepository: AppRepository
) : EmailVerificationUseCase {
    override suspend fun executeSendVerifyCodeToUserEmail(page: Int): SendVerifyCodeToUserEmailResult {
        return appRepository.sendVerifyCodeToUserEmail(page = page)
    }

    override suspend fun executePostEmailVerifyCode(code: String): PostEmailVerifyCodeResult {
        return appRepository.postEmailVerifyCode(code = code)
    }
}