package com.example.domain.usecases.implementations

import android.util.Log
import com.example.domain.entity.results.JoinToEventAsFanResultEntity
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.JoinToEventAsFunUseCase
import javax.inject.Inject

class JoinToEventAsFunUseCaseImpl @Inject constructor(internal val appRepository: AppRepository): JoinToEventAsFunUseCase{
    override suspend fun executeJoinRequestAsFun(
        eventId: Int
    ) : JoinToEventAsFanResultEntity {
        Log.d("Timber12", eventId.toString())
        return appRepository.joinToEventAsFan(eventId = eventId)
    }
}