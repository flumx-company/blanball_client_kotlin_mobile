package com.example.data.backend

import com.example.data.backend.models.*
import com.example.data.tokenmanager.TokenManager
import com.example.domain.entity.*
import com.example.domain.repository.LoginRepository
import com.squareup.moshi.Moshi
import retrofit2.HttpException

class LoginRepositoryImpl (private val service: ApiService, private val tokenManager: TokenManager) : LoginRepository  {

    override suspend fun login(email: String, password: String): LoginResultEntity {
        return try {
            val authRequest = AuthRequest(email, password)
            val loginDto = service.loginAuthorization(authRequest)
            val loginResponse = loginDto.toLoginResponse()
            tokenManager.saveToken(loginResponse.data.tokens.access)
            LoginResultEntity.Success(loginResponse.data)
        } catch (ex: HttpException) {
            val errorBody = ex.response()?.errorBody()?.string()
            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter(LoginErrorDto::class.java)
            val errorDto = errorBody?.let { adapter.fromJson(it) }
            val errorResponse = errorDto?.toErrorResponse()
            LoginResultEntity.Error(errorResponse!!.data.errors[0])
        }
    }

    private fun LoginSuccesDto.toLoginResponse(): LoginResponse {

        return LoginResponse(this.code, this.data.toLoginData(), this.message, this.status)
    }

    private fun Data.toLoginData(): LoginData {
        return LoginData(this.email, this.tokens.toLoginTokens())
    }

    private fun Tokens.toLoginTokens(): LoginTokens {
        return LoginTokens(this.access, this.refresh)
    }

    private fun LoginErrorDto.toErrorResponse(): ErrorResponse {
        return ErrorResponse(this.code, this.data.toDataError(), this.message, this.status)
    }

    private fun DataError.toDataError(): LoginDataError {
        return LoginDataError(listOf(this.errors[0].toLoginError()), this.type)
    }

    private fun Error.toLoginError(): LoginError {
        return LoginError(this.detail)
    }
}