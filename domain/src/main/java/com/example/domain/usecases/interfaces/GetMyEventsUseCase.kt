package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetMyEventsResultEntity

interface GetMyEventsUseCase {
    suspend fun executeGetMyEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetMyEventsResultEntity
}