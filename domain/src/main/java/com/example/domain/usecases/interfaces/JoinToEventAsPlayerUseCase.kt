package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.JoinToEventAsPlayerResultEntity

interface JoinToEventAsPlayerUseCase {
    suspend fun executeJoinRequestAsPlayer(eventId: Int): JoinToEventAsPlayerResultEntity
}