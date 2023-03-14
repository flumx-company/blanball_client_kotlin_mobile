package com.example.data.backend


import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.LoginDto
import com.example.domain.BuildConfig
import retrofit2.http.Body
import retrofit2.http.POST


interface  ApiService  {

    @POST(BuildConfig.LOGIN_ENDPOINT)
    suspend fun loginAuthorization(@Body authRequest: AuthRequest): LoginDto

}