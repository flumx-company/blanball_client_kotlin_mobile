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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2NTE2MTU5LCJpYXQiOjE2ODY1MTU4NTksImp0aSI6Ijc1NThkNjQ2ZmNhZDQzODhhZDQ4ZTg5MmExODk5MWNhIiwidXNlcl9pZCI6MzUxfQ.6RBlVo6f4qmDpmtNx9TwdcWkR2xodNJxT12Kc2UUAQM")
         return chain.proceed(request.build())
    }
}