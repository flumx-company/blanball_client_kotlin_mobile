package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUserReviewsByIdResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUserReviewsByIdUseCase
import javax.inject.Inject

class GetUserReviewsByIdUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetUserReviewsByIdUseCase {
    override suspend fun executeGetUserReviewsById(page: Int): GetUserReviewsByIdResult {
        return appRepository.getUserReviewsById(id = 34, page = page)
    }
}

// TODO: id will match the selected user when going to their public profile