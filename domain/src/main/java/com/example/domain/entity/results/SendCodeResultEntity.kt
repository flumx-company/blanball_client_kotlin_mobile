package com.example.domain.entity.results

import com.example.domain.entity.responses.DataSendCodeDomain
import com.example.domain.entity.responses.SendCodeErrorsEntity

sealed class SendCodeResultEntity {
    data class Success(val data: DataSendCodeDomain) : SendCodeResultEntity()
    data class Error(val error: SendCodeErrorsEntity) : SendCodeResultEntity()
}
