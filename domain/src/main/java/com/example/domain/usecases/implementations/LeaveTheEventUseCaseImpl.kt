package com.example.domain.usecases.implementations

import com.example.domain.entity.results.LeaveTheEventAsFanResultEntity
import com.example.domain.entity.results.LeaveTheEventAsPlayerResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.LeaveTheEventUseCase
import javax.inject.Inject

class LeaveTheEventUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) :
    LeaveTheEventUseCase {
    override suspend fun leaveTheEventRequestAsFan(eventId: Int): LeaveTheEventAsFanResultEntity {
        return appRepository.leaveTheEventAsFan(eventId = eventId)
    }

    override suspend fun leaveTheEventRequestAsPlayer(eventId: Int): LeaveTheEventAsPlayerResultEntity {
        return appRepository.leaveTheEventAsPlayer(eventId = eventId)
    }
}