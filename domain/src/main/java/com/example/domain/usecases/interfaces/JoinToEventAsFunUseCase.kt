package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.JoinToEventAsFunResultEntity

interface JoinToEventAsFunUseCase {
   suspend fun executeJoinRequestAsFun(eventId: Int): JoinToEventAsFunResultEntity
}