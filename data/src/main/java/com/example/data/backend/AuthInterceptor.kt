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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0NDA2MDc3LCJpYXQiOjE2ODQ0MDU3NzcsImp0aSI6IjI1OWU1OTc2OGI0MjQyOWJhNDc5YjFmOTVhNWEwYjI1IiwidXNlcl9pZCI6MzUxfQ.Q1mvcCYLA3p3dGwIkrt35H4ZOwxfDrYD319L51ZRGlc")
         return chain.proceed(request.build())
    }
}