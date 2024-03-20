package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntityDataDetail
import com.example.domain.entity.responses.success.SendVerifyCodeToUserEmailResponseEntityData

sealed class SendVerifyCodeToUserEmailResultEntity {
    data class Success(val data: SendVerifyCodeToUserEmailResponseEntityData) : SendVerifyCodeToUserEmailResultEntity()
    data class Error(val error: SendVerifyCodeToUserEmailErrorEntityDataDetail) : SendVerifyCodeToUserEmailResultEntity()
}
