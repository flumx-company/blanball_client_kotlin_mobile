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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2Njg0ODY2LCJpYXQiOjE2ODY2ODQ1NjYsImp0aSI6IjAwYTVhOGIwNWUzNjRiMTE4OGNiNjk4ZDJhYTYzODcxIiwidXNlcl9pZCI6MzUxfQ.PObeu_sSnZPT0_Nyi6vMvvrGQm3wfYNnpyqEqh1Q6xA")
         return chain.proceed(request.build())
    }
}