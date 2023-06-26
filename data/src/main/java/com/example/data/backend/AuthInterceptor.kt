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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk2NzkwODQyLCJpYXQiOjE2ODc3OTA4NDIsImp0aSI6IjMzZTExYmZjNzQ3YjQ2ZDA4YjJmYjZmOTEwYzhjYTM5IiwidXNlcl9pZCI6MTh9.Yb9glulpcbA27x26kShTlYlVbtkYYVag5tXfs4964hQ")
         return chain.proceed(request.build())
    }
}