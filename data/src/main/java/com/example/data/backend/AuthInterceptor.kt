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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk4NjE2OTQ3LCJpYXQiOjE2ODk2MTY5NDcsImp0aSI6ImMwYWZjYmY5NzhmNjRmNjM4NjdlMmQ4N2EyZGY2ZGFkIiwidXNlcl9pZCI6MX0.g_Ag9S7e_TuouBgCZX35SBVDsvlxWaDKpz4Cf7WIb0o")
        return chain.proceed(request.build())
    }
}