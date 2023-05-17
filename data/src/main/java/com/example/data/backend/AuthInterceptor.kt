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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0MzQwNTgwLCJpYXQiOjE2ODQzNDAyODAsImp0aSI6IjVmZDA0YTY5NzBmMDQxYTQ4MzYxZTBiMTIxM2RiNWIxIiwidXNlcl9pZCI6MzUxfQ.0ckNHHgNBESBGTAzjOEsS9gGmA6jUAdcWKnTNs20SUM")
         return chain.proceed(request.build())
    }
}