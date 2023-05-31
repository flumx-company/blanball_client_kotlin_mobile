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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg1NTY3MjM2LCJpYXQiOjE2ODU1NjY5MzYsImp0aSI6IjBkNjQzODJiZTE4ZjRhZGM5NjAxZDExZmM5Y2YwMTU0IiwidXNlcl9pZCI6MzUxfQ.adrYZQv76B1xjQ-mSK4Jh0QaT2hfejjWezFNa92Gw")
         return chain.proceed(request.build())
    }
}