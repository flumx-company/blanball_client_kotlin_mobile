package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUserProfileByIdResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUserProfileByIdUseCase
import javax.inject.Inject

class GetUserProfileByIdUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetUserProfileByIdUseCase {
    override suspend fun executeGetUserProfileById(userId: Int): GetUserProfileByIdResult {
        return appRepository.getUserProfileById(userId)
    }
}