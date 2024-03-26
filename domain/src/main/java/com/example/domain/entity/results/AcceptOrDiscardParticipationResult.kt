package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.AcceptOrDiscardParticipationErrorEntityDetail
import com.example.domain.entity.responses.success.AcceptOrDiscardParticipationResponseEntity

sealed class AcceptOrDiscardParticipationResult {
    data class Success(val data: AcceptOrDiscardParticipationResponseEntity) :
        AcceptOrDiscardParticipationResult()

    data class Error(val error: AcceptOrDiscardParticipationErrorEntityDetail) :
        AcceptOrDiscardParticipationResult()
}
