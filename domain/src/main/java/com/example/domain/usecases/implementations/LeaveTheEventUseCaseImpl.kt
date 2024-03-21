package com.example.domain.usecases.implementations

import com.example.domain.entity.results.LeaveTheEventAsFanResult
import com.example.domain.entity.results.LeaveTheEventAsPlayerResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.LeaveTheEventUseCase
import javax.inject.Inject

class LeaveTheEventUseCaseImpl @Inject constructor(internal val appRepository: AppRepository) :
    LeaveTheEventUseCase {
    override suspend fun leaveTheEventRequestAsFan(eventId: Int): LeaveTheEventAsFanResult {
        return appRepository.leaveTheEventAsFan(eventId = eventId)
    }

    override suspend fun leaveTheEventRequestAsPlayer(eventId: Int): LeaveTheEventAsPlayerResult {
        return appRepository.leaveTheEventAsPlayer(eventId = eventId)
    }
}