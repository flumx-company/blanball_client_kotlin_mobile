package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.JoinToEventAsFanResult

interface JoinToEventAsFunUseCase {
   suspend fun executeJoinRequestAsFun(eventId: Int): JoinToEventAsFanResult
}