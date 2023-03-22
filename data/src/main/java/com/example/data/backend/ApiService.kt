package com.example.data.backend


import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.LoginSucces
import com.example.domain.utils.AuthEndpoints
import retrofit2.http.Body
import retrofit2.http.POST


interface  ApiService  {

    @POST(AuthEndpoints.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginSucces
}