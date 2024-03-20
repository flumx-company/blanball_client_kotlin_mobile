package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.LeaveTheEventAsFanResultEntity
import com.example.domain.entity.results.LeaveTheEventAsPlayerResultEntity

interface LeaveTheEventUseCase {
    suspend fun leaveTheEventRequestAsFan(eventId: Int): LeaveTheEventAsFanResultEntity
    suspend fun leaveTheEventRequestAsPlayer(eventId: Int): LeaveTheEventAsPlayerResultEntity
}