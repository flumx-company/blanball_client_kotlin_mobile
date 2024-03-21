package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetMyProfileResult

interface GetMyProfileUseCase {
    suspend fun executeGetMyProfile(page: Int) : GetMyProfileResult
}