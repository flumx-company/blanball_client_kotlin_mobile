package com.example.domain.usecases.implementations

import com.example.domain.entity.results.AcceptOrDiscardParticipationResult
import com.example.domain.entity.results.GetPrivateEventRequestListResult
import com.example.domain.repository.AppRepository
import com.example.domain.usecases.interfaces.UserRequestingForPrivateEventUseCase
import javax.inject.Inject

class UserRequestingForPrivateEventUseCaseImpl @Inject constructor(val appRepository: AppRepository) :
    UserRequestingForPrivateEventUseCase {
    override suspend fun executeGetPrivateEventRequestList(eventId: Int): GetPrivateEventRequestListResult {
        return appRepository.getPrivateEventRequestList(eventId = eventId)
    }

    override suspend fun executeAcceptingDiscardingEventRequest(
        isAcceptEventRequest: Boolean,
        ids: List<Int>
    ): AcceptOrDiscardParticipationResult {
        return appRepository.acceptOrDiscardParticipation(
            isAcceptEventRequest = isAcceptEventRequest, ids = ids,
        )
    }
}