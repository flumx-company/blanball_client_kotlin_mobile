package com.example.data.backend

import com.example.data.backend.models.*
import com.example.data.tokenmanager.TokenManager
import com.example.domain.entity.*
import com.example.domain.repository.AppRepository
import com.squareup.moshi.Moshi
import retrofit2.HttpException

class AppRepositoryImpl (private val service: ApiService, private val tokenManager: TokenManager) : AppRepository  {

    override suspend fun sendEmailPassReset(email: String): EmailResetResultEntity {
        return try {
            val request = SendEmailPasswordResetRequest(email)
            val sendEmailResetResponse = service.sendEmailPasswordReset(request)
            val sendEmailResetDomainResponse = sendEmailResetResponse.toEmailResetResponse()
            EmailResetResultEntity.Success(sendEmailResetDomainResponse.data)
        } catch (ex: HttpException) {
            handleSendEmailError(ex)
        }
    }

    override suspend fun login(email: String, password: String): LoginResultEntity {
        return try {
            val authRequest = AuthRequest(email, password)
            val loginSuccess = service.loginAuthorization(authRequest)
            val loginResponse = loginSuccess.toLoginResponse()
            tokenManager.saveToken(loginResponse.data.tokens.access)
            LoginResultEntity.Success(loginResponse.data)
        } catch (ex: HttpException) {
            handleLoginError(ex)
        }
    }

    private fun handleSendEmailError(ex: HttpException): EmailResetResultEntity {
        val errorBody = ex.response()?.errorBody()?.string()
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(EmailPassResetError::class.java)
        val errorDto = errorBody?.let { adapter.fromJson(it) }
        val errorResponse = errorDto?.toEmailPassResetErrorEntity()
        return EmailResetResultEntity.Error(errorResponse!!.data.errors[0])
    }

    private fun handleLoginError(ex: HttpException): LoginResultEntity {
        val errorBody = ex.response()?.errorBody()?.string()
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(LoginError::class.java)
        val errorDto = errorBody?.let { adapter.fromJson(it) }
        val errorResponse = errorDto?.toErrorResponse()
        return LoginResultEntity.Error(errorResponse!!.data.errors[0])
    }


    private fun EmailPassResetError.toEmailPassResetErrorEntity() : EmailPassResetErrorEntity {
        return EmailPassResetErrorEntity(this.code, this.data.toEmailPassDataErrorEntity(), this.message, this.status)
    }

    private fun EmailPassDataError.toEmailPassDataErrorEntity() : EmailPassDataErrorEntity{
        return  EmailPassDataErrorEntity(listOf(this.errors[0].toEmailPassDataErrorEntity()), this.type)
    }

    private fun EmailPassResetErrors.toEmailPassDataErrorEntity() : EmailPassResetErrorsEntity{
        return  EmailPassResetErrorsEntity(this.detail)
    }

    private fun SendEmailPasswordResetSuccess.toEmailResetResponse() : EmailResetResponseEntity {
        return EmailResetResponseEntity(this.code, this.data.toDataEmailReset(), this.message, this.status)
    }

    private fun DataEmailReset.toDataEmailReset() : DataEmailResetEntity {
        return  DataEmailResetEntity(this.success)
    }

    private fun LoginSucces.toLoginResponse(): LoginResponse {

        return LoginResponse(this.code, this.data.toLoginData(), this.message, this.status)
    }

    private fun Data.toLoginData(): LoginData {
        return LoginData(this.email, this.tokens.toLoginTokens())
    }

    private fun Tokens.toLoginTokens(): LoginTokens {
        return LoginTokens(this.access, this.refresh)
    }

    private fun LoginError.toErrorResponse(): ErrorResponse {
        return ErrorResponse(this.code, this.data.toDataError(), this.message, this.status)
    }

    private fun DataError.toDataError(): LoginDataError {
        return LoginDataError(listOf(this.errors[0].toLoginError()), this.type)
    }

    private fun Error.toLoginError(): LoginErrorDomain {
        return LoginErrorDomain(this.detail)
    }
}