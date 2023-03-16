package com.example.data.backend


import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.LoginSuccesDto
import com.example.domain.utils.Const
import retrofit2.http.Body
import retrofit2.http.POST


interface  ApiService  {

    @POST(Const.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSuccesDto
}