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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg1NzAzMjQ1LCJpYXQiOjE2ODU3MDI5NDUsImp0aSI6IjQ0ZTliOGViNDYzMDRiMGM4NzNkZjA4MDEzZmNkNjVlIiwidXNlcl9pZCI6MzUxfQ.imATAX7dkVVKwx1mXFIQN5nKLocMPv94FdUjVN7T0Zs")
         return chain.proceed(request.build())
    }
}