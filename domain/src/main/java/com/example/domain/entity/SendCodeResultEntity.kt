package com.example.domain.entity

sealed class SendCodeResultEntity {
    data class Success(val data: DataSendCodeDomain) : SendCodeResultEntity()
    data class Error(val error: SendCodeErrorsEntity) : SendCodeResultEntity()
}
