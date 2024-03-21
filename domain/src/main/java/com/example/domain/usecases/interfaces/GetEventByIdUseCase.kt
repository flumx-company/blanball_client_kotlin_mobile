package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetEventByIdResult

interface GetEventByIdUseCase {
    suspend fun executeGetEventById(
        userId: Int
    ): GetEventByIdResult
}