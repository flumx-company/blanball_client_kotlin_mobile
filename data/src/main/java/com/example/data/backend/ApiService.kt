package com.example.data.backend

import com.example.data.backend.models.AuthRequest
import com.example.data.backend.models.AuthResponseDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private val retrofit = Retrofit.Builder()
    .baseUrl("178.151.201.167:49299/api/v1" )
    .addConverterFactory(MoshiConverterFactory.create())
    .client(getRetrofitClient())
    .build()

private fun getRetrofitClient(): OkHttpClient {



    return OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder().also {
                it.addHeader("Accept", "application/json")
            }.build())
        }.also { client ->
            client.addInterceptor(AuthInterceptor(T))
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }.build()
}

abstract class ApiService {

    @POST("/authentication/client/login")
    abstract suspend fun loginAuthorization(@Body authRequest: AuthRequest): AuthResponseDto

    object ApiObject {
        val retrofitService: ApiService = retrofit.create(ApiService::class.java)
    }
}