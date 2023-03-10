package com.example.data.backend

import android.os.RemoteException
import com.example.data.backend.models.*
import com.example.data.datastore.TokenManager
import com.example.domain.entity.*
import com.example.domain.repository.LoginRepository
import retrofit2.HttpException

class LoginRepositoryImpl (private val service: ApiService, private val tokenManager: TokenManager) : LoginRepository  {

    override suspend fun login(username: String, password: String): LoginResultEntity {
        return try {
            val authRequest = AuthRequest(username, password)

            val authResponseDto = service.loginAuthorization(authRequest)

            val loginResponse = authResponseDto.toLoginResponse()

            tokenManager.saveToken(loginResponse.data.tokens.access)

            LoginResultEntity.Success(loginResponse.data)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Error")
        }
    }

    private fun AuthResponseDto.toLoginResponse(): LoginResponse{
        return LoginResponse(this.code, this.data.toLoginData(), this.message, this.status)
    }

    private fun Data.toLoginData(): LoginData {
        return LoginData(this.email, this.tokens.toLoginTokens(), this.type, this.errors[0].toLoginError())
    }

    private fun Tokens.toLoginTokens(): LoginTokens {
        return LoginTokens(this.access, this.refresh)
    }

    private fun Error.toLoginError(): LoginError{
        return LoginError(this.detail)
    }
}