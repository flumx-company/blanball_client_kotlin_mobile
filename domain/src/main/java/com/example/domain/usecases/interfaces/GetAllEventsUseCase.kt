package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetAllEventsResultEntity

interface GetAllEventsUseCase {
    suspend fun executeGetAllEvents(page: Int): GetAllEventsResultEntity
}