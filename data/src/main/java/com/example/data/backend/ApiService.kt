package com.example.data.backend

import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.AuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST


abstract class  ApiService  {

    @POST("/authentication/client/login")
    abstract suspend fun loginAuthorization(@Body authRequest: AuthRequest): AuthResponseDto

}