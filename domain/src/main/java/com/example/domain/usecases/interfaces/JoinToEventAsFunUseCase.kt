package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.JoinToEventAsFanResultEntity

interface JoinToEventAsFunUseCase {
   suspend fun executeJoinRequestAsFun(eventId: Int): JoinToEventAsFanResultEntity
}