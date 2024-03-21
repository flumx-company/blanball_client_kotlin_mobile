package com.example.domain.entity.results

import com.example.domain.entity.responses.errors.SendVerifyCodeToUserEmailErrorEntityDataDetail
import com.example.domain.entity.responses.success.SendVerifyCodeToUserEmailResponseEntityData

sealed class SendVerifyCodeToUserEmailResult {
    data class Success(val data: SendVerifyCodeToUserEmailResponseEntityData) : SendVerifyCodeToUserEmailResult()
    data class Error(val error: SendVerifyCodeToUserEmailErrorEntityDataDetail) : SendVerifyCodeToUserEmailResult()
}
