package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity

interface GetUserPlannedEventsByIdUseCase {
    suspend fun executeGetUserPlannedEventsById(): GetUserPlannedEventsByIdResultEntity
}