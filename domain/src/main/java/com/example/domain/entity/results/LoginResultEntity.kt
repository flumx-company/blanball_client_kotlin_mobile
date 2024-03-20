package com.example.domain.entity.results

import com.example.domain.entity.responses.success.LoginData
import com.example.domain.entity.responses.success.LoginErrorsDomain

sealed class LoginResultEntity {
    data class Success(val data: LoginData) : LoginResultEntity()
    data class Error(val error: LoginErrorsDomain) : LoginResultEntity()
}