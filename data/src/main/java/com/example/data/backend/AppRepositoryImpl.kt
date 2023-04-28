package com.example.data.backend

import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.Data
import com.example.data.backend.models.DataEmailReset
import com.example.data.backend.models.DataError
import com.example.data.backend.models.DataResetCompleteError
import com.example.data.backend.models.DataResetCompleteResponse
import com.example.data.backend.models.DataSendCode
import com.example.data.backend.models.EmailPassDataError
import com.example.data.backend.models.EmailPassResetError
import com.example.data.backend.models.EmailPassResetErrors
import com.example.data.backend.models.LoginError
import com.example.data.backend.models.LoginErrors
import com.example.data.backend.models.LoginSucces
import com.example.data.backend.models.Profile
import com.example.data.backend.models.RegistrationData
import com.example.data.backend.models.RegistrationError
import com.example.data.backend.models.RegistrationErrorDetail
import com.example.data.backend.models.RegistrationErrorsData
import com.example.data.backend.models.RegistrationRequest
import com.example.data.backend.models.RegistrationResponse
import com.example.data.backend.models.ResetCompleteError
import com.example.data.backend.models.ResetCompleteErrors
import com.example.data.backend.models.ResetCompleteRequest
import com.example.data.backend.models.ResetCompleteResponse
import com.example.data.backend.models.SendCodeDataError
import com.example.data.backend.models.SendCodeError
import com.example.data.backend.models.SendCodeErrors
import com.example.data.backend.models.SendCodeResponse
import com.example.data.backend.models.SendEmailPasswordResetRequest
import com.example.data.backend.models.SendEmailPasswordResetSuccess
import com.example.data.backend.models.SendResetCodeRequest
import com.example.data.backend.models.Tokens
import com.example.data.tokenmanager.TokenManager
import com.example.data.verifycodemanager.VerifyCodeManager
import com.example.domain.entity.DataCompleteResponseEntity
import com.example.domain.entity.DataEmailResetEntity
import com.example.domain.entity.DataSendCodeDomain
import com.example.domain.entity.EmailPassDataErrorEntity
import com.example.domain.entity.EmailPassResetErrorEntity
import com.example.domain.entity.EmailPassResetErrorsEntity
import com.example.domain.entity.EmailResetResponseEntity
import com.example.domain.entity.EmailResetResultEntity
import com.example.domain.entity.ErrorResponse
import com.example.domain.entity.LoginData
import com.example.domain.entity.LoginDataError
import com.example.domain.entity.LoginErrorsDomain
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.LoginResultEntity
import com.example.domain.entity.LoginTokens
import com.example.domain.entity.RegistrationDataEntity
import com.example.domain.entity.RegistrationErrorDetailEntity
import com.example.domain.entity.RegistrationErrorEntity
import com.example.domain.entity.RegistrationErrorsDataEntity
import com.example.domain.entity.RegistrationResponseEntity
import com.example.domain.entity.RegistrationResultEntity
import com.example.domain.entity.ResetCompleteDataEntity
import com.example.domain.entity.ResetCompleteErrorEntity
import com.example.domain.entity.ResetCompleteErrorsEntity
import com.example.domain.entity.ResetCompleteResponseEntity
import com.example.domain.entity.ResetCompleteResultEntity
import com.example.domain.entity.SendCodeDataErrorEntity
import com.example.domain.entity.SendCodeErrorEntity
import com.example.domain.entity.SendCodeErrorsEntity
import com.example.domain.entity.SendCodeResponseEntity
import com.example.domain.entity.SendCodeResultEntity
import com.example.domain.repository.AppRepository
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.HttpException
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    internal val service: ApiService,
    internal val tokenManager: TokenManager,
    internal val verifyCodeManager: VerifyCodeManager,
) : AppRepository {


    override suspend fun changePassword(newPassword: String): ResetCompleteResultEntity {
        return try {
            val savedVerifyCode = verifyCodeManager.getVerifyCode().firstOrNull()?.toString() ?: ""
            val request = ResetCompleteRequest(newPassword, savedVerifyCode)
            val resetCompleteResponse = service.resetComplete(request)
            val resetCompleteDomainResponse = resetCompleteResponse.toResetCompleteResponseEntity()
            ResetCompleteResultEntity.Success(resetCompleteDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<ResetCompleteError, ResetCompleteErrorEntity>(ex) { it.toResetCompleteErrorEntity() }
            ResetCompleteResultEntity.Error(errorResponse.data.errors[0])
        }

    }

    override suspend fun sendCode(code: String): SendCodeResultEntity {
        return try {
            val request = SendResetCodeRequest(code)
            val sendResetCodeResponse = service.validateResetCode(request)
            val sendResetCodeDomainResponse = sendResetCodeResponse.toSendCodeResponseEntity()
            verifyCodeManager.saveVerifyCode(code)
            SendCodeResultEntity.Success(sendResetCodeDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<SendCodeError, SendCodeErrorEntity>(ex) { it.toSendCodeErrorEntity() }
            SendCodeResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun sendEmailPassReset(email: String): EmailResetResultEntity {
        return try {
            val request = SendEmailPasswordResetRequest(email)
            val sendEmailResetResponse = service.sendEmailPasswordReset(request)
            val sendEmailResetDomainResponse = sendEmailResetResponse.toEmailResetResponse()
            EmailResetResultEntity.Success(sendEmailResetDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<EmailPassResetError, EmailPassResetErrorEntity>(ex) { it.toEmailPassResetErrorEntity() }
            EmailResetResultEntity.Error(errorResponse.data.errors[0])
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
            val errorResponse =
                handleHttpError<LoginError, ErrorResponse>(ex) { it.toErrorResponse() }
            LoginResultEntity.Error(errorResponse.data.errors[0])
        }
    }

        override suspend fun registration(
            email: String,
            phone: String,
            password: String,
            re_password: String,
            name: String,
            lastName: String,
            gender: String,
        ): RegistrationResultEntity {
            return try {
                val request = RegistrationRequest(email =
                    email, password = password, phone = phone, profile =
                    Profile (name = name, last_name = lastName, gender = gender), re_password = re_password,
                )
                val registrationSuccess = service.userRegistration(request)
                val registrationResponse = registrationSuccess.toRegistrationResponseEntity()
                RegistrationResultEntity.Success(registrationResponse.data)
            } catch (ex: HttpException) {
                val errorResponse = handleHttpError<RegistrationError, RegistrationErrorEntity>(ex) { it.toRegistrationErrorEntity() }
                RegistrationResultEntity.Error(errorResponse.data.errors[0])
            }
        }

    private inline fun <reified T, R> handleHttpError(
        ex: HttpException,
        errorMapper: (T) -> R
    ): R {
        val errorBody = ex.response()?.errorBody()?.string()
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(T::class.java)
        val errorDto = errorBody?.let { adapter.fromJson(it) }
        val errorResponse = errorDto?.let { errorMapper(it) }
        return errorResponse ?: error("Unknown error")
    }

    private fun RegistrationError.toRegistrationErrorEntity(): RegistrationErrorEntity {
        return RegistrationErrorEntity(
            this.code,
            this.data.toRegistrationErrorsDataEntity(),
            this.message,
            this.status,
        )
    }


    private fun RegistrationErrorsData.toRegistrationErrorsDataEntity(): RegistrationErrorsDataEntity {
        return RegistrationErrorsDataEntity(
            listOf(this.errors[0].toRegistrationErrorDetailEntity()),
            this.type
        )
    }

    private fun RegistrationErrorDetail.toRegistrationErrorDetailEntity(): RegistrationErrorDetailEntity {
        return RegistrationErrorDetailEntity(this.detail)
    }

    private fun RegistrationResponse.toRegistrationResponseEntity(): RegistrationResponseEntity {
        return RegistrationResponseEntity(
            this.code,
            this.data.toRegistrationDataEntity(),
            this.message,
            this.status
        )
    }


    private fun RegistrationData.toRegistrationDataEntity(): RegistrationDataEntity {
        return RegistrationDataEntity(this.access, this.refresh)
    }

    private fun ResetCompleteError.toResetCompleteErrorEntity(): ResetCompleteErrorEntity {
        return ResetCompleteErrorEntity(
            this.code,
            this.data.toResetCompleteDataEntity(),
            this.message,
            this.status
        )
    }

    private fun DataResetCompleteError.toResetCompleteDataEntity(): ResetCompleteDataEntity {
        return ResetCompleteDataEntity(
            listOf(this.errors[0].toResetCompleteErrorsEntity()),
            this.type
        )
    }


    private fun ResetCompleteErrors.toResetCompleteErrorsEntity(): ResetCompleteErrorsEntity {
        return ResetCompleteErrorsEntity(this.detail)
    }


    private fun DataResetCompleteResponse.toResetCompleteResponseEntity(): DataCompleteResponseEntity {
        return DataCompleteResponseEntity(this.success)
    }

    private fun ResetCompleteResponse.toResetCompleteResponseEntity(): ResetCompleteResponseEntity {
        return ResetCompleteResponseEntity(
            this.code,
            this.data.toResetCompleteResponseEntity(),
            this.message,
            this.status
        )
    }

    private fun SendCodeError.toSendCodeErrorEntity(): SendCodeErrorEntity {
        return SendCodeErrorEntity(
            this.code,
            this.data.toSendCodeDataErrorEntity(),
            this.message,
            this.status
        )
    }

    private fun SendCodeDataError.toSendCodeDataErrorEntity(): SendCodeDataErrorEntity {
        return SendCodeDataErrorEntity(
            listOf(this.errors[0].toSendCodeErrorsEntity()),
            this.type
        )
    }

    private fun SendCodeErrors.toSendCodeErrorsEntity(): SendCodeErrorsEntity {
        return SendCodeErrorsEntity(this.detail)
    }

    private fun SendCodeResponse.toSendCodeResponseEntity(): SendCodeResponseEntity {
        return SendCodeResponseEntity(
            this.code,
            this.data.toDataSendDomain(),
            this.message,
            this.status
        )
    }

    private fun DataSendCode.toDataSendDomain(): DataSendCodeDomain {
        return DataSendCodeDomain(this.success)
    }

    private fun EmailPassResetError.toEmailPassResetErrorEntity(): EmailPassResetErrorEntity {
        return EmailPassResetErrorEntity(
            this.code,
            this.data.toEmailPassDataErrorEntity(),
            this.message,
            this.status
        )
    }

    private fun EmailPassDataError.toEmailPassDataErrorEntity(): EmailPassDataErrorEntity {
        return EmailPassDataErrorEntity(
            listOf(this.errors[0].toEmailPassDataErrorEntity()),
            this.type
        )
    }

    private fun EmailPassResetErrors.toEmailPassDataErrorEntity(): EmailPassResetErrorsEntity {
        return EmailPassResetErrorsEntity(this.detail)
    }

    private fun SendEmailPasswordResetSuccess.toEmailResetResponse(): EmailResetResponseEntity {
        return EmailResetResponseEntity(
            this.code,
            this.data.toDataEmailReset(),
            this.message,
            this.status
        )
    }

    private fun DataEmailReset.toDataEmailReset(): DataEmailResetEntity {
        return DataEmailResetEntity(this.success)
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
        return LoginDataError(listOf(this.errors[0].toLoginErrors()), this.type)
    }

    private fun LoginErrors.toLoginErrors(): LoginErrorsDomain {
        return LoginErrorsDomain(this.detail)
    }
}