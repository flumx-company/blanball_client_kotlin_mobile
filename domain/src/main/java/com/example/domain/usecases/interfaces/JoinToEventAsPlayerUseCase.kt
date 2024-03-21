package com.example.domain.usecases.interfaces

import com.example.domain.entity.results.JoinToEventAsPlayerResult

interface JoinToEventAsPlayerUseCase {
    suspend fun executeJoinRequestAsPlayer(eventId: Int): JoinToEventAsPlayerResult
}