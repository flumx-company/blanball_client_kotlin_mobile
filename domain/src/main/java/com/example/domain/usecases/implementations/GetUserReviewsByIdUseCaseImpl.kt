package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
import javax.inject.Inject

class GetUserReviewsByIdUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetUserReviewsByIdUseCase {
    override suspend fun executeGetUserReviewsById(): GetUserReviewsByIdResultEntity {
        return appRepository.getUserReviewsById(2)
    }
}