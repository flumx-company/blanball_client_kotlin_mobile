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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2MDQ3NzI0LCJpYXQiOjE2ODYwNDc0MjQsImp0aSI6IjM2NTg2YmNjZmM3MTQwYTY5ZjUzN2EyMWUwZDEyMTk4IiwidXNlcl9pZCI6MzUxfQ.yRlSvFtBwOurHZtSIWuqw9VhcJ1T8ex0kRE0VPFMJRY")
         return chain.proceed(request.build())
    }
}