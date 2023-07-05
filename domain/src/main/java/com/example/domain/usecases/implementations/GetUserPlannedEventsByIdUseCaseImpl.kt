package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetUserPlannedEventsByIdResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetUserPlannedEventsByIdUseCase
import javax.inject.Inject

class GetUserPlannedEventsByIdUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) :
    GetUserPlannedEventsByIdUseCase {
    override suspend fun executeGetUserPlannedEventsById(page: Int): GetUserPlannedEventsByIdResultEntity {
        return appRepository.getUserPlannedEventsById(34, page = page)
    }
}

// TODO: id will match the selected user when going to their public profile