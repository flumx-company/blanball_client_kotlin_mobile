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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk4NTgwMjgzLCJpYXQiOjE2ODk1ODAyODMsImp0aSI6ImExZjdiMDU4ZmRlNzQ1M2M5YzQ0NmQ2NzhlYzIwMjc5IiwidXNlcl9pZCI6MX0.6r5sp-MIhYWRcjhzGvVLDdLphL03bu24uYAO3hJSG5E")
        return chain.proceed(request.build())
    }
}