package com.example.data.backend

import com.example.data.tokenmanager.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (
    private val tokenManager: TokenManager,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        val request = chain.request().newBuilder()
        request.addHeader("Accept", "application/json")
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2NzM5NDM2LCJpYXQiOjE2ODY3MzkxMzYsImp0aSI6ImExYjE4ZWQyNTBhYzQwNjA5YmQ3YmE0ZTY2NjQzNDdjIiwidXNlcl9pZCI6MzUxfQ.afR7g-gSB_oekrB35Uxf_UPLEKfR5f6Dc6iLFoDmGcE")
         return chain.proceed(request.build())
    }
}