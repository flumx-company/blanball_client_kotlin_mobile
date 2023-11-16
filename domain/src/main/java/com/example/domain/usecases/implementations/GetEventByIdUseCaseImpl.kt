package com.example.domain.usecases.implementations

import com.example.domain.entity.results.GetEventByIdResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.GetEventByIdUseCase
import javax.inject.Inject

class GetEventByIdUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) :
    GetEventByIdUseCase {
    override suspend fun executeGetEventById(userId: Int): GetEventByIdResultEntity {
        return appRepository.getEventById(userId)
    }
}