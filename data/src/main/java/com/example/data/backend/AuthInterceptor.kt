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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2NjYyNTg4LCJpYXQiOjE2ODY2NjIyODgsImp0aSI6ImI2NTRiMDJkNTEwZTQ1MjFiODYxMjE0ZDQyODRjNzEyIiwidXNlcl9pZCI6MzUxfQ.ls05K64hdexi1EqvVPmIkn9ZeSRIQqzbtSdF6HlEvSM")
         return chain.proceed(request.build())
    }
}