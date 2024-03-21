package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.GetPrivateEventRequestListResult

interface UserRequestingForPrivateEventUseCase {
    suspend fun executeGetPrivateEventRequestList(eventId: Int) : GetPrivateEventRequestListResult
}