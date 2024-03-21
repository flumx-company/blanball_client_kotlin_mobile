package com.example.domain.entity.results

import com.example.domain.entity.responses.success.DataSendCodeDomain
import com.example.domain.entity.responses.success.SendCodeErrorsEntity

sealed class SendCodeResult {
    data class Success(val data: DataSendCodeDomain) : SendCodeResult()
    data class Error(val error: SendCodeErrorsEntity) : SendCodeResult()
}
