package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUserReviewsByIdResult

interface GetUserReviewsByIdUseCase {
    suspend fun executeGetUserReviewsById (page: Int) : GetUserReviewsByIdResult
}