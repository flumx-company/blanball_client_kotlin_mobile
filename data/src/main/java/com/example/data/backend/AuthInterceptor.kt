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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg1NDg3NzE3LCJpYXQiOjE2ODU0ODc0MTcsImp0aSI6IjRjZWE0NjBmY2MzYjQ0ZGFiNWQwZDRhZjEwZTgwNTE3IiwidXNlcl9pZCI6MzUxfQ.pLgnNmvHOb6GQw7ecaNqxobfVuMmhjfgv8w0GwZ52_8")
         return chain.proceed(request.build())
    }
}