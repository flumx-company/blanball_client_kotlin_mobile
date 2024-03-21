package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetAllEventsResult

interface GetAllEventsUseCase {
    suspend fun executeGetAllEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String,
        filterDateAndTimeBefore: String,
        filterDateAndTimeAfter: String,
    ): GetAllEventsResult
}