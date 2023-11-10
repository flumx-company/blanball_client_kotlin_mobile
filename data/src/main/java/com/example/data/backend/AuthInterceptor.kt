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
        val request = chain.request().newBuilder()
        request.addHeader("Accept", "application/json")
        runBlocking {
            tokenManager.getAccessToken().first()?.let { token ->
                request.addHeader("Authorization", "Bearer $token")
            }
        }
        return chain.proceed(request.build())
    }
}