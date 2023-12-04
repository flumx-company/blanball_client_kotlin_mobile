package com.example.domain.entity.results

import com.example.domain.entity.responses.SendVerifyCodeToUserEmailErrorEntity
import com.example.domain.entity.responses.SendVerifyCodeToUserEmailErrorEntityDataDetail
import com.example.domain.entity.responses.SendVerifyCodeToUserEmailResponseEntityData

sealed class SendVerifyCodeToUserEmailResultEntity {
    data class Success(val data: SendVerifyCodeToUserEmailResponseEntityData) : SendVerifyCodeToUserEmailResultEntity()
    data class Error(val error: SendVerifyCodeToUserEmailErrorEntityDataDetail) : SendVerifyCodeToUserEmailResultEntity()
}
