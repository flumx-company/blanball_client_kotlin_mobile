package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUserReviewsByIdResultEntity

interface GetUserReviewsByIdUseCase {
    suspend fun executeGetUserReviewsById (page: Int) : GetUserReviewsByIdResultEntity
}