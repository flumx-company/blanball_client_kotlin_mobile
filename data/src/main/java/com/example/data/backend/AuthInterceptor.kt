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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk2Nzg5NDA1LCJpYXQiOjE2ODc3ODk0MDUsImp0aSI6ImMwZDU0ZTEwMmEwNDRhNmVhOGRhZjhiYWI4YTRjYTRiIiwidXNlcl9pZCI6MTZ9.kV6Pz3TjP9iS4Iq5ZKjVSiDlAh9Hb2sJFTQn8OLxyUw")
         return chain.proceed(request.build())
    }
}