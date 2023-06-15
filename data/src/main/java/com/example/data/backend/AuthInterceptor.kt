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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2ODE3NzA1LCJpYXQiOjE2ODY4MTc0MDUsImp0aSI6IjJjYjhjNWY3ZWQ5ODQ4YTBhNjVhMjY3YTk1YzhkMTc3IiwidXNlcl9pZCI6MzUxfQ.DtUR6-BhS3rFl8Sc3-ahkKnLZKSCsRl5_F5qRj_gUxg")
        return chain.proceed(request.build())
    }
}