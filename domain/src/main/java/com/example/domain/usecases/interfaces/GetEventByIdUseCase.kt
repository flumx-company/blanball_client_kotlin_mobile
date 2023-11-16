package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetEventByIdResultEntity

interface GetEventByIdUseCase {
    suspend fun executeGetEventById(
        userId: Int
    ): GetEventByIdResultEntity
}