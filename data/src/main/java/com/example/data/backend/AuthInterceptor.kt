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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg0ODE1NzA3LCJpYXQiOjE2ODQ4MTU0MDcsImp0aSI6ImMyNWUxZDIxYzNjNzRhODdiNTJmYmE0MmFjZjhhNmY4IiwidXNlcl9pZCI6MzUxfQ.BVmbfSFZ6tYkozH05Cr7yJVi2Ze6GrwVAuxrEiGDFxg")
         return chain.proceed(request.build())
    }
}