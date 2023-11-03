package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetMyEventsResultEntity

interface GetMyEventsUseCase {
    suspend fun executeMyEventsEvents(
        page: Int,
        typeOfSport: String,
        gender: String,
        time_and_date: String,
        ordering: String
    ): GetMyEventsResultEntity
}