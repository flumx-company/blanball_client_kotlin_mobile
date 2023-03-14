package com.example.data.backend

import android.os.RemoteException
import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.Data
import com.example.data.backend.models.LoginDto
import com.example.data.backend.models.Tokens
import com.example.data.datastore.TokenManager
import com.example.domain.entity.LoginData
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.LoginResultEntity
import com.example.domain.entity.LoginTokens
import com.example.domain.repository.LoginRepository
import retrofit2.HttpException

class LoginRepositoryImpl (private val service: ApiService, private val tokenManager: TokenManager) : LoginRepository  {

    override suspend fun login(email: String, password: String): LoginResultEntity {
        return try {
            val authRequest = AuthRequest(email, password)

            val authResponseDto = service.loginAuthorization(authRequest)

            val loginResponse = authResponseDto.toLoginResponse()

            tokenManager.saveToken(loginResponse.data.tokens.access)

            LoginResultEntity.Success(loginResponse.data)
        } catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Error")
        }
    }

    private fun LoginDto.toLoginResponse(): LoginResponse {

        return LoginResponse(this.code, this.data.toLoginData(), this.message, this.status)
    }

    private fun Data.toLoginData(): LoginData {
        return LoginData(this.email, this.tokens.toLoginTokens())
    }

    private fun Tokens.toLoginTokens(): LoginTokens {
        return LoginTokens(this.access, this.refresh)
    }
}