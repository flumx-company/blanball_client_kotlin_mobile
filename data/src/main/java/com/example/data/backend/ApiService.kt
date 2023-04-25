package com.example.data.backend


import com.example.data.backend.models.*
import com.example.domain.utils.Endpoints
import retrofit2.http.Body
import retrofit2.http.POST


interface  ApiService  {

    @POST(Endpoints.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSucces

    @POST(Endpoints.SEND_EMAIL_PASSWORD_RESET_ENDPOINT)
    suspend fun sendEmailPasswordReset(@Body sendEmailPasswordResetRequest: SendEmailPasswordResetRequest) : SendEmailPasswordResetSuccess

    @POST(Endpoints.VALIDATE_RESET_CODE_ENDPOINT)
    suspend fun validateResetCode(@Body sendResetCodeRequest: SendResetCodeRequest): SendCodeResponse

    @POST (Endpoints.RESET_COMPLETE_ENDPOINT)
    suspend fun resetComplete(@Body resetCompleteRequest: ResetCompleteRequest): ResetCompleteResponse

    @POST (Endpoints.REGISTER_ENDPOINT)
    suspend fun userRegistration(@Body registrationRequest: RegistrationRequest): RegistrationResponse
}