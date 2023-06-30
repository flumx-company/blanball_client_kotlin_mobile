package com.example.data.backend

import com.example.data.datastore.tokenmanager.TokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor (
    private val tokenManager: TokenManager,
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getToken().first()
        }
        val request = chain.request().newBuilder()
        request.addHeader("Accept", "application/json")
        request.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjoxNjk2ODI3NjYyLCJpYXQiOjE2ODc4Mjc2NjIsImp0aSI6IjkxMzMyOTMwYWVkNjQ3MGFhN2M5NzA0NDMxZDFiNWIwIiwidXNlcl9pZCI6MzJ9.tgzOKenCFbSbkyRfyu1Rzx3fFA3__iPOQI3fx_ENN08") //TODO: Temporarily, manual token replacement.Need to implement the logic of authorization tokens.
         return chain.proceed(request.build())
    }
}