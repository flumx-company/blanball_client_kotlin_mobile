package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.LeaveTheEventAsFanResult
import com.example.domain.entity.results.LeaveTheEventAsPlayerResult

interface LeaveTheEventUseCase {
    suspend fun leaveTheEventRequestAsFan(eventId: Int): LeaveTheEventAsFanResult
    suspend fun leaveTheEventRequestAsPlayer(eventId: Int): LeaveTheEventAsPlayerResult
}