package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetMyProfileResultEntity

interface GetMyProfileUseCase {
    suspend fun executeGetMyProfile(page: Int) : GetMyProfileResultEntity
}