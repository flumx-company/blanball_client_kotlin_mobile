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
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk4NTI2OTM5LCJpYXQiOjE2ODk1MjY5MzksImp0aSI6ImU5YmQ0YmYyNmI5NzRhZWFhZTg4MzVmYTIyM2MxMzE2IiwidXNlcl9pZCI6MX0.DcXMgQBIJSODxCSUyCxnmfTqxrkQraRVcYaonGODVn4")
        return chain.proceed(request.build())
    }
}