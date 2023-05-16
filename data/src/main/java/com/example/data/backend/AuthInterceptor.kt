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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0MjQ3NTUxLCJpYXQiOjE2ODQyNDcyNTEsImp0aSI6ImYxYjQ5YTFkYmVhNjQ2ZDg5N2E3YzUzNjg2MjVkMGM5IiwidXNlcl9pZCI6MzUxfQ.nsikLlxrMfQhvCe3mdmmb3m48vxlm7LIbuibE_KaVug")
         return chain.proceed(request.build())
    }
}