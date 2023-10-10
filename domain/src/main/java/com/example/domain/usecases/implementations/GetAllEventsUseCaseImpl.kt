package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetAllEventsResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetAllEventsUseCase
import javax.inject.Inject

class GetAllEventsUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) : GetAllEventsUseCase {
    override suspend fun executeGetAllEvents(page: Int): GetAllEventsResultEntity {
        return appRepository.getAllEvents(page)
    }
}