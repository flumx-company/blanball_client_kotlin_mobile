package com.example.domain.usecases.implementations

import com.example.domain.entity.results.JoinToEventAsFunResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.JoinToEventAsFunUseCase
import javax.inject.Inject

class JoinToEventAsFunUseCaseImpl @Inject constructor(internal val appRepository: AppRepository): JoinToEventAsFunUseCase{
    override suspend fun executeJoinRequestAsFun(
        eventId: Int
    ) : JoinToEventAsFunResultEntity {
        return appRepository.joinToEventAsFun(eventId = eventId)
    }
}