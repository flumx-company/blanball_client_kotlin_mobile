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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2NzcxNjY4LCJpYXQiOjE2ODY3NzEzNjgsImp0aSI6Ijc5MGU4MDdkNGZmOTQ4OGViMjFjNDJlZTgzZDZhMzg1IiwidXNlcl9pZCI6MzUxfQ.-36o_LDXWxiovCF6cBg1oQnWyBeKAgtWIYl1AK1hL4E")
        return chain.proceed(request.build())
    }
}