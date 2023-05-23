package com.example.data.backend

import android.util.Log
import com.example.data.backend.models.requests.AuthRequest
import com.example.data.backend.models.requests.ProfileRegistrationRequest
import com.example.data.backend.models.requests.RegistrationRequest
import com.example.data.backend.models.requests.ResetCompleteRequest
import com.example.data.backend.models.requests.SendEmailPasswordResetRequest
import com.example.data.backend.models.requests.SendResetCodeRequest
import com.example.data.backend.models.responses.EmailPassResetError
import com.example.data.backend.models.responses.GetUserProfileByIdError
import com.example.data.backend.models.responses.GetUserReviewsByIdResponseError
import com.example.data.backend.models.responses.LoginError
import com.example.data.backend.models.responses.RegistrationError
import com.example.data.backend.models.responses.ResetCompleteError
import com.example.data.backend.models.responses.SendCodeError
import com.example.data.tokenmanager.TokenManager
import com.example.data.utils.ext.toEmailPassResetErrorEntity
import com.example.data.utils.ext.toEmailResetResponse
import com.example.data.utils.ext.toErrorResponse
import com.example.data.utils.ext.toGetUserProfileByIdErrorEntity
import com.example.data.utils.ext.toGetUserProfileByIdResponseEntity
import com.example.data.utils.ext.toGetUserReviewsByIdResponseEntity
import com.example.data.utils.ext.toGetUserReviewsByIdResponseErrorEntity
import com.example.data.utils.ext.toLoginResponse
import com.example.data.utils.ext.toRegistrationErrorEntity
import com.example.data.utils.ext.toRegistrationResponseEntity
import com.example.data.utils.ext.toResetCompleteErrorEntity
import com.example.data.utils.ext.toResetCompleteResponseEntity
import com.example.data.utils.ext.toSendCodeErrorEntity
import com.example.data.utils.ext.toSendCodeResponseEntity
import com.example.data.verifycodemanager.VerifyCodeManager
import com.example.domain.entity.responses.EmailPassResetErrorEntity
import com.example.domain.entity.responses.ErrorResponse
import com.example.domain.entity.responses.GetUserProfileByIdErrorEntity
import com.example.domain.entity.responses.GetUserReviewsByIdResponseErrorEntity
import com.example.domain.entity.responses.RegistrationErrorEntity
import com.example.domain.entity.responses.ResetCompleteErrorEntity
import com.example.domain.entity.responses.SendCodeErrorEntity
import com.example.domain.entity.results.EmailResetResultEntity
import com.example.domain.entity.results.GetUserProfileByIdResultEntity
import com.example.domain.entity.results.GetUserReviewsByIdResultEntity
import com.example.domain.entity.results.LoginResultEntity
import com.example.domain.entity.results.RegistrationResultEntity
import com.example.domain.entity.results.ResetCompleteResultEntity
import com.example.domain.entity.results.SendCodeResultEntity
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

    override suspend fun getUserReviewsById(id: Int): GetUserReviewsByIdResultEntity {
        return try {
            val getUserReviewsByIdResponse = service.getUserReviewsById(id)
            Log.d("MyLOG", service.getUserReviewsById(id).toString())
            val getUserReviewsByIdDomainResponse =
                getUserReviewsByIdResponse.toGetUserReviewsByIdResponseEntity()
            GetUserReviewsByIdResultEntity.Success(getUserReviewsByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse = handleHttpError<GetUserReviewsByIdResponseError, GetUserReviewsByIdResponseErrorEntity>(ex) { it.toGetUserReviewsByIdResponseErrorEntity() }
            GetUserReviewsByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

    override suspend fun getUserProfileById(id: Int): GetUserProfileByIdResultEntity {
        return try {
            val getUserProfileByIdResponse = service.getUserProfileById(id)
            val getUserProfileByIdDomainResponse =
                getUserProfileByIdResponse.toGetUserProfileByIdResponseEntity()
            GetUserProfileByIdResultEntity.Success(getUserProfileByIdDomainResponse.data)
        } catch (ex: HttpException) {
            val errorResponse =
                handleHttpError<GetUserProfileByIdError, GetUserProfileByIdErrorEntity>(ex) { it.toGetUserProfileByIdErrorEntity() }
            GetUserProfileByIdResultEntity.Error(errorResponse.data.errors[0])
        }
    }

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
                val request = RegistrationRequest(
                    email =
                    email,
                    password = password, phone = phone,
                    profile =
                    ProfileRegistrationRequest(name = name, last_name = lastName, gender = gender),
                    re_password = re_password,
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
}