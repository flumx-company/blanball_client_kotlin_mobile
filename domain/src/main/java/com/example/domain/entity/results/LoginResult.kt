package com.example.domain.entity.results
import com.example.domain.entity.responses.success.LoginData
import com.example.domain.entity.responses.success.LoginErrorsDomain

sealed class LoginResult {
    data class Success(val data: LoginData) : LoginResult()
    data class Error(val error: LoginErrorsDomain) : LoginResult()
}