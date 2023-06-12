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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2NTU4NzU5LCJpYXQiOjE2ODY1NTg0NTksImp0aSI6ImU0MTE0OWM4MzI3OTRhZTNiNWUxYmZmZTI5OGQwYzcxIiwidXNlcl9pZCI6MzUxfQ.zb_VQbaCfISMmE7D9WyRgI12g4Tl60Nj6lXTyuYtcws")
         return chain.proceed(request.build())
    }
}