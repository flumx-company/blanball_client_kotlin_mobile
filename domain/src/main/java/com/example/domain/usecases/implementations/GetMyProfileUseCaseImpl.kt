package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetMyProfileResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetMyProfileUseCase
import javax.inject.Inject

class GetMyProfileUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetMyProfileUseCase {
    override suspend fun executeGetMyProfile(page: Int): GetMyProfileResultEntity {
        return appRepository.getMyProfile(page)
    }
}