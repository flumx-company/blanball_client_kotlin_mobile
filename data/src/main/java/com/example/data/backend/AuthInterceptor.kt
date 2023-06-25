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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk2NzI0MDE0LCJpYXQiOjE2ODc3MjQwMTQsImp0aSI6ImY0ODJkMjJhY2I4MTQ1MTliMTc0ZWUzZDQ5YWRkNWQyIiwidXNlcl9pZCI6MX0.Y3U87SrC6drRrbyom9RSGTbtZWKx9LT8lCoLhw0ra68")
         return chain.proceed(request.build())
    }
}