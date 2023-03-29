package com.example.data.backend


import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.LoginSucces
import com.example.data.backend.models.SendEmailPasswordResetRequest
import com.example.data.backend.models.SendEmailPasswordResetSuccess
import com.example.domain.utils.Endpoints
import retrofit2.http.Body
import retrofit2.http.POST


interface  ApiService  {

    @POST(Endpoints.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSucces

    @POST(Endpoints.SEND_EMAIL_PASSWORD_RESET_ENDPOINT)
    suspend fun sendEmailPasswordReset(@Body sendEmailPasswordResetRequest: SendEmailPasswordResetRequest) : SendEmailPasswordResetSuccess
}