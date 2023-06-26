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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk2Nzg1MjA4LCJpYXQiOjE2ODc3ODUyMDgsImp0aSI6IjlkZTQ3ZTJmZWExNzQ4Yjc4NTA2NjFiZDdlNGI5NDUxIiwidXNlcl9pZCI6MTR9.-f9yA-eoLLEKmO4s8dppVx-iKj9tVgQVlQY651cCP2w")
         return chain.proceed(request.build())
    }
}