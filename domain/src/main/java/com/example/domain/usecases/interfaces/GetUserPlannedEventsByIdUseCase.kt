package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUserPlannedEventsByIdResult

interface GetUserPlannedEventsByIdUseCase {
    suspend fun executeGetUserPlannedEventsById(page: Int): GetUserPlannedEventsByIdResult
}