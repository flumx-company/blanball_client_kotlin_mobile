package com.example.domain.usecases.implementations

import com.example.domain.entity.results.LoginResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.UserLoginUseCase
import javax.inject.Inject

class UserLoginUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : UserLoginUseCase {
    override suspend fun executeUserLogin(email: String, password: String): LoginResultEntity {
        return  appRepository.login(email, password)
    }
}