package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUserProfileByIdResultEntity

interface GetUserProfileByIdUseCase {
    suspend fun executeGetUserProfileById (
    ): GetUserProfileByIdResultEntity
}