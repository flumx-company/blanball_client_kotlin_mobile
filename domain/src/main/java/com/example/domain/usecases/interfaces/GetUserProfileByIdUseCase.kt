package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUserProfileByIdResult

interface GetUserProfileByIdUseCase {
    suspend fun executeGetUserProfileById (
        userId: Int
    ): GetUserProfileByIdResult
}