package com.example.data.backend


import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.LoginSucces
import com.example.data.backend.models.ResetPassRequest
import com.example.data.backend.models.ResetPassRequestSuccess
import com.example.domain.utils.Endpoints
import retrofit2.http.Body
import retrofit2.http.POST


interface  ApiService  {

    @POST(Endpoints.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSucces

    @POST(Endpoints.PASSWORD_RESET_ENDPOINT)
    suspend fun requestPasswordResetting(@Body resetPassRequest: ResetPassRequest) : ResetPassRequestSuccess
}