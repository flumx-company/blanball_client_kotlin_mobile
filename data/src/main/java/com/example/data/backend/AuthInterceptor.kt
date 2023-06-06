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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjg2MDQ2ODM1LCJpYXQiOjE2ODYwNDY1MzUsImp0aSI6ImU0MTk3MDU2MTk2YTQxNGE5YTQyNmUyMTViYTY2MjI2IiwidXNlcl9pZCI6MzUxfQ.XUc_ZEs_qGfquK9f43Pa6o1cUok8RuWt485EjAopFAE")
         return chain.proceed(request.build())
    }
}