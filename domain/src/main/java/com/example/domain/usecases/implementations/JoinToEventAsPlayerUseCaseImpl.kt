package com.example.domain.usecases.implementations

import com.example.domain.entity.results.JoinToEventAsPlayerResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.JoinToEventAsPlayerUseCase
import javax.inject.Inject

class JoinToEventAsPlayerUseCaseImpl @Inject constructor(internal val appRepository: AppRepository):
    JoinToEventAsPlayerUseCase {
    override suspend fun executeJoinRequestAsPlayer(
        eventId: Int
    ) : JoinToEventAsPlayerResultEntity {
        return appRepository.joinToEventAsPlayer(eventId = eventId)
    }
}